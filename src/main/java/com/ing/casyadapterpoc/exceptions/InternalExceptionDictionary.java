package com.ing.casyadapterpoc.exceptions;

public enum InternalExceptionDictionary {

    EXCEPTION("T0001", "Error");


    private final String code;
    private final String reason;

    InternalExceptionDictionary(String code, String reason) {
        this.code = code;
        this.reason = reason;

    }

    public String getMessage() {
        return String.join(":", code, reason);
    }

}


