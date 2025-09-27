package com.geopokrovskiy.rest;

import com.geopokrovskiy.dto.payments_service.transaction.request.PrepareTransactionDto;
import com.geopokrovskiy.entity.PaymentMethod;
import com.geopokrovskiy.service.PaymentMethodService;
import com.geopokrovskiy.service.transaction.TransactionService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/transaction")
@Data
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    private final PaymentMethodService paymentMethodService;
    private final Map<String, TransactionService> services;

    @PostMapping("/{paymentMethodId}")
    public ResponseEntity<?> createTransaction(@RequestBody PrepareTransactionDto requestBody,
                                               @PathVariable Long paymentMethodId) throws Exception {
        PaymentMethod paymentMethod = paymentMethodService.getPaymentMethodById(paymentMethodId);
        String paymentMethodName = paymentMethod.getName();
        TransactionService transactionService = services.get(paymentMethodName);
        return new ResponseEntity<>(transactionService.createTransaction(paymentMethod, requestBody), HttpStatusCode.valueOf(201));
    }
}