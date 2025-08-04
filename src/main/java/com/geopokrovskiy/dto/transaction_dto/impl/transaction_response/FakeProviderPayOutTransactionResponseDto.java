package com.geopokrovskiy.dto.transaction_dto.impl.transaction_response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.geopokrovskiy.dto.transaction_dto.TransactionResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class FakeProviderPayOutTransactionResponseDto extends TransactionResponseDto {
    private UUID id;
    private String transactionStatus;
}
