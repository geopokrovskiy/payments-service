package com.geopokrovskiy.mapper;

import com.geopokrovskiy.dto.payments_service.payment_method.PaymentMethodResponseDto;
import com.geopokrovskiy.entity.PaymentMethod;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {

    PaymentMethodResponseDto map(PaymentMethod paymentMethod);
}
