package com.tsystems.javaschool.uberbahn.services.errors;


public class BusinessLogicException extends RuntimeException {

    public BusinessLogicException(String errorMsg) {
        super(errorMsg);
    }

    public BusinessLogicException(String errorMsg, Throwable ex) {
        super(errorMsg, ex);
    }
}