package com.geopokrovskiy.service.transaction;

import com.geopokrovskiy.dto.transaction_dto.CreateTransactionDto;
import com.geopokrovskiy.dto.transaction_dto.TransactionResponseDto;
import com.geopokrovskiy.dto.transaction_dto.impl.create_transaction.FakeProviderTopUpCardDto;
import com.geopokrovskiy.dto.transaction_dto.impl.create_transaction.FakeProviderTopUpCreateTransactionDto;
import com.geopokrovskiy.dto.transaction_dto.impl.create_transaction.FakeProviderTopUpCustomerDto;
import com.geopokrovskiy.entity.PaymentMethod;
import com.geopokrovskiy.entity.Transaction;
import com.geopokrovskiy.service.PaymentMethodService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

@Data
@Service("fake-provider-top-up")
@Transactional
@Slf4j
public class FakeProviderTopUpCreateTransactionServiceImpl implements TransactionService {

    private final PaymentMethodService paymentMethodService;

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${providers.fake-provider.methods.top-up.notification-url.host}")
    private String NOTIFICATION_URL_HOST;

    @Value("${providers.fake-provider.methods.top-up.notification-url.port}")
    private String NOTIFICATION_URL_PORT;

    @Value("${providers.fake-provider.methods.top-up.provider-url.host}")
    private String PROVIDER_URL_HOST;

    @Value("${providers.fake-provider.methods.top-up.provider-url.port}")
    private String PROVIDER_URL_PORT;

    @Value("${providers.fake-provider.methods.top-up.provider-url.uri}")
    private String PROVIDER_URL_URI;

    @Override
    public CreateTransactionDto createTransaction(PaymentMethod paymentMethod, Transaction transaction) {
        Map<String, String> filledRequiredFields = verifyRequiredFields(paymentMethod, transaction);

        FakeProviderTopUpCardDto fakeProviderCardDto = new FakeProviderTopUpCardDto();
        fakeProviderCardDto.setCvv(filledRequiredFields.get("cvv"));
        fakeProviderCardDto.setCardNumber(filledRequiredFields.get("card_number"));
        fakeProviderCardDto.setExpirationDate(filledRequiredFields.get("expiration_date"));

        FakeProviderTopUpCustomerDto fakeProviderTopUpCustomerDto = new FakeProviderTopUpCustomerDto();
        fakeProviderTopUpCustomerDto.setFirstName(filledRequiredFields.get("first_name"));
        fakeProviderTopUpCustomerDto.setLastName(filledRequiredFields.get("last_name"));
        fakeProviderTopUpCustomerDto.setCountry(filledRequiredFields.get("country"));
        fakeProviderTopUpCustomerDto.setUsername(filledRequiredFields.get("username"));

        FakeProviderTopUpCreateTransactionDto createTransactionDto = new FakeProviderTopUpCreateTransactionDto();
        createTransactionDto.setAmount(Double.parseDouble(filledRequiredFields.get("amount")));

        createTransactionDto.setCard(fakeProviderCardDto);
        createTransactionDto.setCustomer(fakeProviderTopUpCustomerDto);

        createTransactionDto.setTransactionType("TOP_UP");
        createTransactionDto.setTransactionStatus("IN_PROGRESS");
        createTransactionDto.setLanguage("en");

        createTransactionDto.setUpdatedAt(LocalDateTime.now());
        createTransactionDto.setCreatedAt(LocalDateTime.now());

        createTransactionDto.setNotificationURL("http://" + NOTIFICATION_URL_HOST + ":" + NOTIFICATION_URL_PORT);

        createTransactionDto.setUsername(filledRequiredFields.get("username"));
        createTransactionDto.setPassword(filledRequiredFields.get("password"));
        createTransactionDto.setAccountId(UUID.fromString(filledRequiredFields.get("account_id")));

        log.info("The transaction has been successfully created");
        return createTransactionDto;
    }

    @Override
    public String processTransaction(CreateTransactionDto createTransactionDto) {
        FakeProviderTopUpCreateTransactionDto fakeProviderTopUpCreateTransactionDto = ((FakeProviderTopUpCreateTransactionDto) createTransactionDto);
        String url = "http://" + PROVIDER_URL_HOST + ":" + PROVIDER_URL_PORT + PROVIDER_URL_URI;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String credentials = fakeProviderTopUpCreateTransactionDto.getUsername() + ":" + fakeProviderTopUpCreateTransactionDto.getPassword();
        String encodedAuth = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
        headers.set("Authorization", "Basic " + encodedAuth);

        HttpEntity<CreateTransactionDto> createTransactionDtoHttpEntity = new HttpEntity<>(fakeProviderTopUpCreateTransactionDto, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, createTransactionDtoHttpEntity, String.class);

        return responseEntity.getBody();
    }

    @Override
    public PaymentMethod getPaymentMethodByName(String name) {
        if (paymentMethodService.getPaymentMethodByName(name).isEmpty()) {
            throw new IllegalArgumentException("Payment method not found");
        }
        return paymentMethodService.getPaymentMethodByName(name).get();
    }
}


