package com.enigma.restservice.exeptions;

public class ApplicationException extends RuntimeException {

    private Integer code;

    public ApplicationException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public ApplicationException(String message, Throwable cause, Integer code) {
        super(message, cause);
        this.code = code;
    }

    public ApplicationException(Throwable cause, Integer code) {
        super(cause);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
