package com.example.tezis.util.exceptions;

public class SheetNotFoundException extends Exception {

    public SheetNotFoundException() {
        super();
    }

    public SheetNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SheetNotFoundException(Throwable cause) {
        super(cause);
    }

    protected SheetNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SheetNotFoundException(String message) {
        super(message);
    }
}
