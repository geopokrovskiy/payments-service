package com.geopokrovskiy.dto.transaction_dto;

import lombok.Data;

import java.util.Map;

@Data
public abstract class PrepareTransactionDto {

    public Map<String, String> fields;
}
