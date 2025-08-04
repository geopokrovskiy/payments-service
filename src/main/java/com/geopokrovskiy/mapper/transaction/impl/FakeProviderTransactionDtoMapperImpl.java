package com.geopokrovskiy.mapper.transaction.impl;

import com.geopokrovskiy.dto.transaction_dto.PrepareTransactionDto;
import com.geopokrovskiy.entity.PaymentMethodRequiredFields;
import com.geopokrovskiy.entity.Transaction;
import com.geopokrovskiy.mapper.transaction.PrepareTransactionDtoMapper;
import com.geopokrovskiy.service.PaymentMethodRequiredFieldsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
@Data
@AllArgsConstructor
public class FakeProviderTransactionDtoMapperImpl implements PrepareTransactionDtoMapper {

    private PaymentMethodRequiredFieldsService paymentMethodRequiredFieldsService;

    @Override
    public Transaction map(PrepareTransactionDto prepareTransactionDto, String paymentMethodName) {
        Map<String, String> fields = prepareTransactionDto.getFields();
        Map<PaymentMethodRequiredFields, String> requiredFieldsStringMap =
                fields.entrySet().stream().collect(Collectors.toMap(
                        entry -> paymentMethodRequiredFieldsService.getRequiredFieldsByNameAndPaymentMethodName(entry.getKey(), paymentMethodName), Map.Entry::getValue)
                );
        return new Transaction(requiredFieldsStringMap);
    }
}
