package com.geopokrovskiy.service;

import com.geopokrovskiy.entity.PaymentProvider;
import com.geopokrovskiy.repository.PaymentProviderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentProviderService {
    private final PaymentProviderRepository paymentProviderRepository;

    public Optional<PaymentProvider> getPaymentProvider(Long id) {
        return paymentProviderRepository.findById(id);
    }

    public List<PaymentProvider> getAllPaymentProviders() {
        return paymentProviderRepository.findAll();
    }
}
