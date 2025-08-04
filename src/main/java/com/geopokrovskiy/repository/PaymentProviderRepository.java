package com.geopokrovskiy.repository;

import com.geopokrovskiy.entity.PaymentProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentProviderRepository extends JpaRepository<PaymentProvider, Long> {
}
