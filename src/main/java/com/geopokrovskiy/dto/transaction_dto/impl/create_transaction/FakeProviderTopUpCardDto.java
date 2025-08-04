package com.geopokrovskiy.dto.transaction_dto.impl.create_transaction;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.geopokrovskiy.dto.transaction_dto.CardDto;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FakeProviderTopUpCardDto implements CardDto {
    private String cardNumber;
    private String cvv;
    private String expirationDate;
}
