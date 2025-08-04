package com.geopokrovskiy.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geopokrovskiy.config.DtoClassResolver;
import com.geopokrovskiy.dto.transaction_dto.CreateTransactionDto;
import com.geopokrovskiy.dto.transaction_dto.PrepareTransactionDto;
import com.geopokrovskiy.dto.transaction_dto.TransactionResponseDto;
import com.geopokrovskiy.entity.PaymentMethod;
import com.geopokrovskiy.entity.Transaction;
import com.geopokrovskiy.mapper.transaction.PrepareTransactionDtoMapper;
import com.geopokrovskiy.service.transaction.TransactionService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/transaction")
@Data
public class TransactionController {

    private final Map<String, TransactionService> services;
    private final ObjectMapper objectMapper;
    private final PrepareTransactionDtoMapper prepareTransactionDtoMapper;
    private final DtoClassResolver dtoClassResolver;

    @Autowired
    public TransactionController(Map<String, TransactionService> services, ObjectMapper objectMapper, PrepareTransactionDtoMapper prepareTransactionDtoMapper, DtoClassResolver dtoClassResolver) {
        this.services = services;
        this.objectMapper = objectMapper;
        this.prepareTransactionDtoMapper = prepareTransactionDtoMapper;
        this.dtoClassResolver = dtoClassResolver;
    }

    @PostMapping("/create")
    public ResponseEntity<TransactionResponseDto> createTransaction(@RequestBody Map<String, Object> requestBody,
                                                                    @RequestParam String paymentMethodName) {

        Class<? extends PrepareTransactionDto> dtoClass = dtoClassResolver.getDtoClassByPaymentMethodName(paymentMethodName);
        PrepareTransactionDto prepareTransactionDto = objectMapper.convertValue(requestBody, dtoClass);

        Transaction transaction = prepareTransactionDtoMapper.map(prepareTransactionDto, paymentMethodName);
        TransactionService transactionService = services.get(paymentMethodName);
        PaymentMethod paymentMethod = transactionService.getPaymentMethodByName(paymentMethodName);

        // creation of transaction
        CreateTransactionDto response = transactionService.createTransaction(paymentMethod, transaction);
        if (response == null) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }

        // transaction processing
        String processedTransaction = transactionService.processTransaction(response);
        Class<? extends TransactionResponseDto> responseDtoClass = dtoClassResolver.getResponseDtoClassByPaymentMethodName(paymentMethodName);
        TransactionResponseDto responseDto;
        try {
            responseDto = objectMapper.readValue(processedTransaction, responseDtoClass);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
        return new ResponseEntity<>(responseDto, HttpStatusCode.valueOf(201));
    }


}
