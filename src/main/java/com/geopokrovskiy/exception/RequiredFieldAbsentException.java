package com.geopokrovskiy.exception;

public class RequiredFieldAbsentException extends Exception {

    private String message;

    public RequiredFieldAbsentException(String message) {
        super(message);
    }
}
