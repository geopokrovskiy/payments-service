package com.geopokrovskiy.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.InvocationTargetException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleResourceNotFound(MissingServletRequestParameterException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleResourceIllegalArgument(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvocationTargetException.class)
    public ResponseEntity<String> handleInvocationTargetException(InvocationTargetException ex) {
        return new ResponseEntity<>(ex.getTargetException().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
