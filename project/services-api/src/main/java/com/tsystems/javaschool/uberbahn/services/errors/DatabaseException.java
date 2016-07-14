package com.tsystems.javaschool.uberbahn.services.errors;


public class DatabaseException extends RuntimeException {
    public DatabaseException(String errorMsg) {
        super(errorMsg);
    }

    public DatabaseException(String errorMsg, Throwable ex) {
        super(errorMsg, ex);
    }
}
