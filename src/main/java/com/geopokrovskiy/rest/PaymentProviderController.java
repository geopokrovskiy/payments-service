package com.geopokrovskiy.rest;

import com.geopokrovskiy.dto.payments_service.payment_provider.PaymentProviderResponseDto;
import com.geopokrovskiy.mapper.PaymentProviderMapper;
import com.geopokrovskiy.service.PaymentProviderService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Data
@RequestMapping("/api/v1/payment_provider")
public class PaymentProviderController {
    private final PaymentProviderService paymentProviderService;
    private final PaymentProviderMapper paymentProviderMapper;

    @GetMapping("/list")
    public ResponseEntity<List<PaymentProviderResponseDto>> getAllPaymentProviders() {
        List<PaymentProviderResponseDto> response = paymentProviderService.getAllPaymentProviders().stream().map(paymentProviderMapper::map).toList();
        return ResponseEntity.ok(response);
    }
}
