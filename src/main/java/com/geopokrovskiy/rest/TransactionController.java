package com.geopokrovskiy.rest;

import com.geopokrovskiy.dto.transaction_dto.TransactionResponseDto;
import com.geopokrovskiy.dto.transaction_dto.impl.transaction_response.error.ErrorTransactionResponseDto;
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
            ErrorTransactionResponseDto responseDto = new ErrorTransactionResponseDto("The payment method id is incorrect",
                    HttpStatusCode.valueOf(404));
            return new ResponseEntity<>(responseDto, HttpStatusCode.valueOf(404));
        }
        PaymentMethod paymentMethod = paymentMethodOptional.get();
        String paymentMethodName = paymentMethod.getName();
        TransactionService transactionService = services.get(paymentMethodName);

        TransactionResponseDto responseDto = transactionService.createTransaction(paymentMethod, requestBody);
        if (responseDto instanceof ErrorTransactionResponseDto) {
            return new ResponseEntity<>(responseDto, ((ErrorTransactionResponseDto) responseDto).getHttpStatusCode());
        }
        return new ResponseEntity<>(responseDto, HttpStatusCode.valueOf(201));
    }


}
