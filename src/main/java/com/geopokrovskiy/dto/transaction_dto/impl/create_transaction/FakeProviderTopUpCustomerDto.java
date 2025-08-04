package com.geopokrovskiy.dto.transaction_dto.impl.create_transaction;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.geopokrovskiy.dto.transaction_dto.CustomerDto;
import lombok.Data;

import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FakeProviderTopUpCustomerDto implements CustomerDto {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String country;
    private String username;
}
