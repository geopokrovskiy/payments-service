package com.geopokrovskiy.service;

import com.geopokrovskiy.entity.PaymentMethodRequiredFields;
import com.geopokrovskiy.repository.PaymentMethodRequiredFieldsRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
@Slf4j
public class PaymentMethodRequiredFieldsService {
    private PaymentMethodRequiredFieldsRepository paymentMethodRequiredFieldsRepository;

    public PaymentMethodRequiredFields getRequiredFieldsByNameAndPaymentMethodName(String name, String paymentMethodName) {
        return paymentMethodRequiredFieldsRepository.findByNameAndPaymentMethodName(name, paymentMethodName)
                .orElse(null);
    }
}
