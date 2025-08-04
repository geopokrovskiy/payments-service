package com.geopokrovskiy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class Transaction {
    Map<PaymentMethodRequiredFields, String> requiredFieldsMap;
}
