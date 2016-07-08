package com.tsystems.javaschool.uberbahn.webmain.exception;


public class CustomGenericException extends Exception {

    private String errorMsg;

    public CustomGenericException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
