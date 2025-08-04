package com.geopokrovskiy.service.transaction;

import com.geopokrovskiy.entity.PaymentMethod;
import com.geopokrovskiy.entity.PaymentMethodRequiredFields;
import com.geopokrovskiy.entity.Transaction;
import com.geopokrovskiy.dto.transaction_dto.CreateTransactionDto;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public interface TransactionService {

    default Map<String, String> verifyRequiredFields(PaymentMethod paymentMethod, Transaction transaction) {
        List<PaymentMethodRequiredFields> paymentMethodRequiredFieldsList = paymentMethod.getRequiredFields();
        Map<PaymentMethodRequiredFields, String> filledRequiredFields = transaction.getRequiredFieldsMap();


        for (PaymentMethodRequiredFields paymentMethodRequiredField : paymentMethodRequiredFieldsList) {
            // check if all required fields have been filled
            if (!filledRequiredFields.containsKey(paymentMethodRequiredField) ||
                    filledRequiredFields.get(paymentMethodRequiredField) == null && paymentMethodRequiredField.isActive()) {
                throw new IllegalArgumentException("Required field " + paymentMethodRequiredField.getName() + " is not filled");
            }

            // check if the required fields have been filled correctly
            String regex = paymentMethodRequiredField.getValidationRule();
            String value = filledRequiredFields.get(paymentMethodRequiredField);
            if (!Pattern.compile(regex).matcher(value).matches()) {
                throw new IllegalArgumentException("Required field " + paymentMethodRequiredField.getName() + " is invalid");
            }
        }
        return filledRequiredFields.entrySet().stream().collect(
                Collectors.toMap(entry -> entry.getKey().getName(), Map.Entry::getValue)
        );
    }

    CreateTransactionDto createTransaction(PaymentMethod paymentMethod, Transaction transaction);

    String processTransaction(CreateTransactionDto createTransactionDto);

    PaymentMethod getPaymentMethodByName(String name);

}
