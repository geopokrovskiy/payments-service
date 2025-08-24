package com.geopokrovskiy.exception;

public class RequiredFieldInvalidException extends Exception {

    private String message;

    public RequiredFieldInvalidException(String message) {
        super(message);
    }

}
