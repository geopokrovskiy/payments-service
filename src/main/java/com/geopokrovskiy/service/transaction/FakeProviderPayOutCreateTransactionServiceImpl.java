package com.geopokrovskiy.service.transaction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geopokrovskiy.dto.transaction_dto.CreateTransactionDto;
import com.geopokrovskiy.dto.transaction_dto.PrepareTransactionDto;
import com.geopokrovskiy.dto.transaction_dto.TransactionResponseDto;
import com.geopokrovskiy.dto.transaction_dto.impl.create_transaction.FakeProviderPayOutCardDto;
import com.geopokrovskiy.dto.transaction_dto.impl.create_transaction.FakeProviderPayOutCreateTransactionDto;
import com.geopokrovskiy.dto.transaction_dto.impl.create_transaction.FakeProviderPayOutCustomerDto;
import com.geopokrovskiy.dto.transaction_dto.impl.prepare_transaction.FakeProviderPayOutPrepareTransactionDto;
import com.geopokrovskiy.dto.transaction_dto.impl.transaction_response.FakeProviderPayOutTransactionResponseDto;
import com.geopokrovskiy.dto.transaction_dto.impl.transaction_response.FakeProviderTopUpTransactionResponseDto;
import com.geopokrovskiy.dto.transaction_dto.impl.transaction_response.error.ErrorTransactionResponseDto;
import com.geopokrovskiy.entity.PaymentMethod;
import com.geopokrovskiy.entity.Transaction;
import com.geopokrovskiy.exception.RequiredFieldAbsentException;
import com.geopokrovskiy.exception.RequiredFieldInvalidException;
import com.geopokrovskiy.mapper.transaction.PrepareTransactionDtoMapper;
import com.geopokrovskiy.service.PaymentMethodService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

@Data
@Service("fake-provider-pay-out")
@Transactional
@Slf4j
public class FakeProviderPayOutCreateTransactionServiceImpl implements TransactionService {

    private final PaymentMethodService paymentMethodService;

    private final RestTemplate restTemplate = new RestTemplate();

    private final ObjectMapper objectMapper;

    private final PrepareTransactionDtoMapper prepareTransactionDtoMapper;

    @Value("${providers.fake-provider.methods.pay-out.notification-url.host}")
    private String NOTIFICATION_URL_HOST;

    @Value("${providers.fake-provider.methods.pay-out.notification-url.port}")
    private String NOTIFICATION_URL_PORT;

    @Value("${providers.fake-provider.methods.pay-out.provider-url.host}")
    private String PROVIDER_URL_HOST;

    @Value("${providers.fake-provider.methods.pay-out.provider-url.port}")
    private String PROVIDER_URL_PORT;

    @Value("${providers.fake-provider.methods.pay-out.provider-url.uri}")
    private String PROVIDER_URL_URI;

    @Override
    public TransactionResponseDto createTransaction(PaymentMethod paymentMethod, Map<String, Object> requestBody) {
        Long paymentMethodId = paymentMethod.getId();

        PrepareTransactionDto prepareTransactionDto = objectMapper.convertValue(requestBody, FakeProviderPayOutPrepareTransactionDto.class);
        Transaction transaction = prepareTransactionDtoMapper.map(prepareTransactionDto, paymentMethodId);
        Map<String, String> filledRequiredFields;
        try {
            filledRequiredFields = verifyRequiredFields(paymentMethod, transaction);
        } catch (RequiredFieldAbsentException | RequiredFieldInvalidException e) {
            return new ErrorTransactionResponseDto(e.getMessage(), HttpStatusCode.valueOf(422));
        }


        FakeProviderPayOutCardDto fakeProviderCardDto = new FakeProviderPayOutCardDto();
        fakeProviderCardDto.setCardNumber(filledRequiredFields.get("card_number"));

        FakeProviderPayOutCustomerDto fakeProviderPayOutCustomerDto = new FakeProviderPayOutCustomerDto();
        fakeProviderPayOutCustomerDto.setFirstName(filledRequiredFields.get("first_name"));
        fakeProviderPayOutCustomerDto.setLastName(filledRequiredFields.get("last_name"));
        fakeProviderPayOutCustomerDto.setCountry(filledRequiredFields.get("country"));
        fakeProviderPayOutCustomerDto.setUsername(filledRequiredFields.get("username"));

        FakeProviderPayOutCreateTransactionDto createTransactionDto = new FakeProviderPayOutCreateTransactionDto();
        createTransactionDto.setAmount(Double.parseDouble(filledRequiredFields.get("amount")));

        createTransactionDto.setCard(fakeProviderCardDto);
        createTransactionDto.setCustomer(fakeProviderPayOutCustomerDto);

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
        return processTransaction(createTransactionDto);
    }


    private TransactionResponseDto processTransaction(CreateTransactionDto createTransactionDto) {
        FakeProviderPayOutCreateTransactionDto fakeProviderPayOutCreateTransactionDto = ((FakeProviderPayOutCreateTransactionDto) createTransactionDto);
        String url = "http://" + PROVIDER_URL_HOST + ":" + PROVIDER_URL_PORT + PROVIDER_URL_URI;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String credentials = fakeProviderPayOutCreateTransactionDto.getUsername() + ":" + fakeProviderPayOutCreateTransactionDto.getPassword();
        String encodedAuth = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
        headers.set("Authorization", "Basic " + encodedAuth);
        ResponseEntity<String> responseEntity;
        try {
            HttpEntity<CreateTransactionDto> createTransactionDtoHttpEntity = new HttpEntity<>(fakeProviderPayOutCreateTransactionDto, headers);
            responseEntity = restTemplate.postForEntity(url, createTransactionDtoHttpEntity, String.class);
            String responseBody = responseEntity.getBody();
            return objectMapper.readValue(responseBody, FakeProviderTopUpTransactionResponseDto.class);
        } catch (HttpClientErrorException e) {
            log.error(e.getMessage());
            return new ErrorTransactionResponseDto("Error during dto serialization", e.getStatusCode());
        } catch (RestClientException e) {
            log.error("The provider is not available");
            return new ErrorTransactionResponseDto("The provider is not available", HttpStatusCode.valueOf(503));
        } catch (IOException e) {
            log.error("Error during dto serialization: {}", e.getMessage());
            return new ErrorTransactionResponseDto("Error during dto serialization", HttpStatusCode.valueOf(400));
        }
    }
}
