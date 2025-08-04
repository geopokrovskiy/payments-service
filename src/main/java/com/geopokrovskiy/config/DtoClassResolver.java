package com.geopokrovskiy.config;

import com.geopokrovskiy.dto.transaction_dto.PrepareTransactionDto;
import com.geopokrovskiy.dto.transaction_dto.TransactionResponseDto;
import com.geopokrovskiy.dto.transaction_dto.impl.prepare_transaction.FakeProviderPayOutPrepareTransactionDto;
import com.geopokrovskiy.dto.transaction_dto.impl.prepare_transaction.FakeProviderTopUpPrepareTransactionDto;
import com.geopokrovskiy.dto.transaction_dto.impl.transaction_response.FakeProviderPayOutTransactionResponseDto;
import com.geopokrovskiy.dto.transaction_dto.impl.transaction_response.FakeProviderTopUpTransactionResponseDto;
import org.springframework.stereotype.Component;

@Component
public class DtoClassResolver {

    public Class<? extends PrepareTransactionDto> getDtoClassByPaymentMethodName(String paymentMethodName) {
        return switch (paymentMethodName) {
            case "fake-provider-top-up" -> FakeProviderTopUpPrepareTransactionDto.class;
            case "fake-provider-pay-out" -> FakeProviderPayOutPrepareTransactionDto.class;
            default -> throw new IllegalArgumentException("Unknown payment method: " + paymentMethodName);
        };
    }

    public Class<? extends TransactionResponseDto> getResponseDtoClassByPaymentMethodName(String paymentMethodName) {
        return switch (paymentMethodName) {
            case "fake-provider-top-up" -> FakeProviderTopUpTransactionResponseDto.class;
            case "fake-provider-pay-out" -> FakeProviderPayOutTransactionResponseDto.class;
            default -> throw new IllegalArgumentException("Unknown payment method: " + paymentMethodName);
        };
    }
}
