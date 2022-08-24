package com.enigma.restservice.controllers;

import com.enigma.restservice.exeptions.ApplicationException;
import com.enigma.restservice.exeptions.ErrorCodes;
import com.enigma.restservice.models.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@ControllerAdvice
public class GlobalExeptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, List<String>> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach((fieldError) -> {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();

            List<String> messages = errors.get(field);
            if (messages == null) {
                messages = new ArrayList();

                errors.put(field, messages);
            }
            messages.add(message);
        });

        ResponseMessage body = ResponseMessage.error(ErrorCodes.ENTITY_NOT_VALID, e.getMessage(), errors);
        return ResponseEntity.ok(body);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ResponseMessage> handleAppException(ApplicationException e) {
        String message = messageSource.getMessage(e.getMessage(), null, LocaleContextHolder.getLocale());
        ResponseMessage body = ResponseMessage.error(e.getCode(), message);
        return ResponseEntity.ok(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage> handleUnknownException(Exception e) {
        ResponseMessage body = ResponseMessage.error(ErrorCodes.UNKNOWN, e.getMessage());
        return ResponseEntity.ok(body);
    }
}
