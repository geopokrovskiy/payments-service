package com.geopokrovskiy.rest;

import com.geopokrovskiy.dto.transaction_dto.TransactionResponseDto;
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
import java.util.Optional;

// TODO TEST CONTAINERS MOCK SERVER
@RestController
@RequestMapping("/api/v1/transaction")
@Data
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    private final PaymentMethodService paymentMethodService;
    private final Map<String, TransactionService> services;

    @PostMapping("/create")
    public ResponseEntity<TransactionResponseDto> createTransaction(@RequestBody Map<String, Object> requestBody,
                                                                    @RequestParam Long paymentMethodId) {
        Optional<PaymentMethod> paymentMethodOptional = paymentMethodService.getPaymentMethodById(paymentMethodId);
        if (paymentMethodOptional.isEmpty()) {
            log.error("The payment method id is incorrect");
            throw new IllegalArgumentException("The payment method is incorrect");
        }
        PaymentMethod paymentMethod = paymentMethodOptional.get();
        String paymentMethodName = paymentMethod.getName();
        TransactionService transactionService = services.get(paymentMethodName);

        TransactionResponseDto responseDto = transactionService.createTransaction(paymentMethod, requestBody);
        return new ResponseEntity<>(responseDto, HttpStatusCode.valueOf(201));
    }


}
