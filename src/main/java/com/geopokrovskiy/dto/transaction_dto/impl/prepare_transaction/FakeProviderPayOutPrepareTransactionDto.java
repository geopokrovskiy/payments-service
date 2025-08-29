package com.geopokrovskiy.dto.transaction_dto.impl.prepare_transaction;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.geopokrovskiy.dto.transaction_dto.PrepareTransactionDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class FakeProviderPayOutPrepareTransactionDto extends PrepareTransactionDto {
    FakeProviderPayOutPrepareTransactionDto(String firstName, String lastName,
                                            String cardNumber, String amount,
                                            String country, String username,
                                            String password, String accountId) {
        Map<String, String> fields = new HashMap<>();
        fields.put("first_name", firstName);
        fields.put("last_name", lastName);
        fields.put("card_number", cardNumber);
        fields.put("amount", amount);
        fields.put("country", country);
        fields.put("username", username);
        fields.put("password", password);
        fields.put("account_id", accountId);

        this.firstName = firstName;
        this.lastName = lastName;
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.country = country;
        this.username = username;
        this.password = password;
        this.accountId = accountId;

        super.requiredFields = fields;
    }

    private String firstName;
    private String lastName;
    private String cardNumber;
    private String amount;
    private String country;
    private String username;
    private String password;
    private String accountId;
}
