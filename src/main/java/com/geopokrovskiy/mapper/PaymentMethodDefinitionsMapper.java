package com.geopokrovskiy.mapper;

import com.geopokrovskiy.dto.payments_service.payment_method.PaymentMethodDefinitionsDto;
import com.geopokrovskiy.entity.PaymentMethodDefinitions;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMethodDefinitionsMapper {
    PaymentMethodDefinitionsDto map(PaymentMethodDefinitions paymentMethodDefinitions);
}
