package com.geopokrovskiy.exception;

public class InvalidPaymentMethodException extends Exception {
    private String message;

    public InvalidPaymentMethodException(String message) {
        super(message);
    }
}
