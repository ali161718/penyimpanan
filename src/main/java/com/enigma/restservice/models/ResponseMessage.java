package com.enigma.restservice.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ResponseMessage<T> {

    private int code;
    private String message;
    private T data;

    @JsonFormat(pattern = "dd-MM-yyy HH:mm:ss.SSS")
    private LocalDateTime timeStamp;

    public ResponseMessage(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timeStamp = LocalDateTime.now();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public static <T> ResponseMessage<T> success(T data) {
        return new ResponseMessage(0, null, data);
    }

    public static <T> ResponseMessage<T> successAdd(T data) {
        return new ResponseMessage(0, "Data successfully added", data);
    }

    public static <T> ResponseMessage<T> successEdit(T data) {
        return new ResponseMessage(0, "Data successfully changed", data);
    }

    public static <T> ResponseMessage<T> successDelete(T data) {
        return new ResponseMessage(0, "Data successfully removed", data);
    }

    public static ResponseMessage error(int code, String message) {
        return new ResponseMessage(code, message, null);
    }

    public static <T> ResponseMessage<T> error(int code, String message, T data) {
        return new ResponseMessage(code, message, data);
    }
}
