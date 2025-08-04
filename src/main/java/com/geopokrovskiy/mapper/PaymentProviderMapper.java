package com.geopokrovskiy.mapper;

import com.geopokrovskiy.dto.payments_service.payment_provider.PaymentProviderResponseDto;
import com.geopokrovskiy.entity.PaymentProvider;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentProviderMapper {
    PaymentProviderResponseDto map(PaymentProvider paymentProvider);
}
