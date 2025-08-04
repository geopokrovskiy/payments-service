package com.geopokrovskiy.service;

import com.geopokrovskiy.entity.PaymentMethod;
import com.geopokrovskiy.repository.PaymentMethodRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
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

    public Optional<PaymentMethod> getPaymentMethodById(Long id) {
        return paymentMethodRepository.findById(id);
    }

    public Optional<PaymentMethod> getPaymentMethodByName(String name) {
        return paymentMethodRepository.findByName(name);
    }
}
