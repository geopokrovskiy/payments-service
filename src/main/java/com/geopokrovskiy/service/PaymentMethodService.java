package com.geopokrovskiy.service;

import com.geopokrovskiy.entity.PaymentMethod;
import com.geopokrovskiy.exception.InvalidPaymentMethodException;
import com.geopokrovskiy.repository.PaymentMethodRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@Slf4j
public class PaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;

    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    public List<PaymentMethod> getPaymentMethodsByCountryAndByCurrency(String alpha3Code, String currency) {
        return paymentMethodRepository.getAllByCountryAndCurrency(alpha3Code, currency);
    }

    public List<PaymentMethod> getPaymentMethodsByCurrency(String currencyCode) {
        return paymentMethodRepository.getAllByCurrency(currencyCode);
    }

    public List<PaymentMethod> getPaymentMethodsByCountry(String alpha3Code) {
        return paymentMethodRepository.getAllByCountry(alpha3Code);
    }

    public PaymentMethod getPaymentMethodById(Long id) throws InvalidPaymentMethodException {
        Optional<PaymentMethod> paymentMethodOptional = paymentMethodRepository.findById(id);
        if (paymentMethodOptional.isEmpty()) {
            log.error("The payment method id is incorrect");
            throw new InvalidPaymentMethodException("Payment id does not exist");
        }
        return paymentMethodOptional.get();
    }

    public Optional<PaymentMethod> getPaymentMethodByName(String name) {
        return paymentMethodRepository.findByName(name);
    }
}
