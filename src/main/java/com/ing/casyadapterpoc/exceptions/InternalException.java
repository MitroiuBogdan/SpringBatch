package com.ing.casyadapterpoc.exceptions;

public class InternalException extends RuntimeException {


    private String message;
    private Throwable exception;

    public InternalException(String message) {
        super(message);
    }

    public InternalException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
