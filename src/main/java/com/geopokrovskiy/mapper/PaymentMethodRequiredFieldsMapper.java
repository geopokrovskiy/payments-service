package com.geopokrovskiy.mapper;

import com.geopokrovskiy.dto.payments_service.payment_method.PaymentMethodRequiredFieldsDto;
import com.geopokrovskiy.entity.PaymentMethodRequiredFields;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMethodRequiredFieldsMapper {
    PaymentMethodRequiredFieldsDto map(PaymentMethodRequiredFields paymentMethodRequiredFields);
}
