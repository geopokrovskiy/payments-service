package com.geopokrovskiy.dto.transaction_dto.impl.prepare_transaction;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.geopokrovskiy.dto.transaction_dto.PrepareTransactionDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class FakeProviderPayOutPrepareTransactionDto extends PrepareTransactionDto {
    FakeProviderPayOutPrepareTransactionDto(String firstName, String lastName,
                                            String cardNumber, Double amount,
                                            String country, String username,
                                            String password, UUID accountId) {
        Map<String, String> fields = new HashMap<>();
        fields.put("first_name", firstName);
        fields.put("last_name", lastName);
        fields.put("card_number", cardNumber);
        fields.put("amount", amount.toString());
        fields.put("country", country);
        fields.put("username", username);
        fields.put("password", password);
        fields.put("account_id", accountId.toString());

        this.firstName = firstName;
        this.lastName = lastName;
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.country = country;
        this.username = username;
        this.password = password;
        this.accountId = accountId;

        super.fields = fields;
    }

    private String firstName;
    private String lastName;
    private String cardNumber;
    private Double amount;
    private String country;
    private String username;
    private String password;
    private UUID accountId;
}
