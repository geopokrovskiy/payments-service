package com.geopokrovskiy.rest;

import com.geopokrovskiy.dto.payments_service.payment_method.PaymentMethodResponseDto;
import com.geopokrovskiy.mapper.PaymentMethodMapper;
import com.geopokrovskiy.service.PaymentMethodService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Data
@RequestMapping("/api/v1/payment_method")
public class PaymentMethodController {
    private final PaymentMethodService paymentMethodService;
    private final PaymentMethodMapper paymentMethodMapper;

    @GetMapping("/list")
    public ResponseEntity<List<PaymentMethodResponseDto>> findAll() {
        List<PaymentMethodResponseDto> response = paymentMethodService.getAllPaymentMethods().stream()
                .map(paymentMethodMapper::map).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<List<PaymentMethodResponseDto>> findAllByCountryAndCurrency(@RequestParam String country, @RequestParam String currency) {
        List<PaymentMethodResponseDto> response = paymentMethodService.getPaymentMethodsByCountryAndByCurrency(country, currency)
                .stream()
                .map(paymentMethodMapper::map)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/country")
    public ResponseEntity<List<PaymentMethodResponseDto>> findAllByCountry(@RequestParam String country) {
        List<PaymentMethodResponseDto> response = paymentMethodService.getPaymentMethodsByCountry(country)
                .stream()
                .map(paymentMethodMapper::map)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/currency")
    public ResponseEntity<List<PaymentMethodResponseDto>> findAllByCurrency(@RequestParam String currency) {
        List<PaymentMethodResponseDto> response = paymentMethodService.getPaymentMethodsByCurrency(currency)
                .stream()
                .map(paymentMethodMapper::map)
                .toList();
        return ResponseEntity.ok(response);
    }
}
