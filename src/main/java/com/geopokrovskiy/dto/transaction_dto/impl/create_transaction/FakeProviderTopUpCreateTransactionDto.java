package com.geopokrovskiy.dto.transaction_dto.impl.create_transaction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.geopokrovskiy.dto.transaction_dto.CardDto;
import com.geopokrovskiy.dto.transaction_dto.CreateTransactionDto;
import com.geopokrovskiy.dto.transaction_dto.CustomerDto;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FakeProviderTopUpCreateTransactionDto implements CreateTransactionDto {
    private UUID uuid;
    private String transactionType;
    private UUID accountId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private CardDto card;
    private CustomerDto customer;
    private String language;
    private Double amount;
    private String notificationURL;
    private String transactionStatus;

    @ToString.Exclude
    @JsonIgnore
    private String username;

    @ToString.Exclude
    @JsonIgnore
    private String password;

}
