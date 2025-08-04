package com.geopokrovskiy.mapper.transaction;

import com.geopokrovskiy.dto.transaction_dto.PrepareTransactionDto;
import com.geopokrovskiy.entity.Transaction;

public interface PrepareTransactionDtoMapper {
    Transaction map(PrepareTransactionDto prepareTransactionDto, String paymentMethodName);
}
