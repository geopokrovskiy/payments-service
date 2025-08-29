package com.geopokrovskiy.errorhandling;

import com.geopokrovskiy.dto.transaction_dto.impl.transaction_response.error.ErrorTransactionResponseDto;
import com.geopokrovskiy.exception.InvalidPaymentMethodException;
import com.geopokrovskiy.exception.RequiredFieldAbsentException;
import com.geopokrovskiy.exception.RequiredFieldInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorTransactionResponseDto> handleResourceNotFound(MissingServletRequestParameterException ex) {
        ErrorTransactionResponseDto transactionResponseDto = new ErrorTransactionResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(transactionResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorTransactionResponseDto> handleResourceIllegalArgument(IllegalArgumentException ex) {
        ErrorTransactionResponseDto transactionResponseDto = new ErrorTransactionResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(transactionResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorTransactionResponseDto> handleIOException(IOException ex) {
        ErrorTransactionResponseDto transactionResponseDto = new ErrorTransactionResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(transactionResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPaymentMethodException.class)
    public ResponseEntity<ErrorTransactionResponseDto> handleInvalidPaymentMethodException(InvalidPaymentMethodException ex) {
        ErrorTransactionResponseDto transactionResponseDto = new ErrorTransactionResponseDto(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(transactionResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvocationTargetException.class)
    public ResponseEntity<ErrorTransactionResponseDto> handleInvocationTargetException(InvocationTargetException ex) {
        ErrorTransactionResponseDto transactionResponseDto = new ErrorTransactionResponseDto(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(transactionResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorTransactionResponseDto> handleRuntimeException(RuntimeException ex) {
        ErrorTransactionResponseDto transactionResponseDto = new ErrorTransactionResponseDto(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(transactionResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ErrorTransactionResponseDto> handleRestClientException(RestClientException ex) {
        ErrorTransactionResponseDto transactionResponseDto = new ErrorTransactionResponseDto(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        return new ResponseEntity<>(transactionResponseDto, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorTransactionResponseDto> handleHttpClientErrorException(HttpClientErrorException ex) {
        ErrorTransactionResponseDto transactionResponseDto = new ErrorTransactionResponseDto(ex.getMessage(), ex.getStatusCode());
        return new ResponseEntity<>(transactionResponseDto, ex.getStatusCode());
    }

    @ExceptionHandler(RequiredFieldAbsentException.class)
    public ResponseEntity<ErrorTransactionResponseDto> handleRequiredFieldAbsentException(RequiredFieldAbsentException ex) {
        ErrorTransactionResponseDto transactionResponseDto = new ErrorTransactionResponseDto(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(transactionResponseDto, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(RequiredFieldInvalidException.class)
    public ResponseEntity<ErrorTransactionResponseDto> handleRequiredFieldInvalidException(RequiredFieldInvalidException ex) {
        ErrorTransactionResponseDto transactionResponseDto = new ErrorTransactionResponseDto(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(transactionResponseDto, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
