package com.geopokrovskiy.repository;

import com.geopokrovskiy.entity.PaymentMethodRequiredFields;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PaymentMethodRequiredFieldsRepository extends JpaRepository<PaymentMethodRequiredFields, Long> {

    @Query("SELECT rf FROM PaymentMethodRequiredFields rf WHERE rf.name = ?1 AND rf.paymentMethod.name = ?2 AND rf.isActive")
    Optional<PaymentMethodRequiredFields> findByNameAndPaymentMethodName(String name, String paymentMethodName);

    @Query("SELECT rf FROM PaymentMethodRequiredFields rf WHERE rf.name = ?1 AND rf.paymentMethod.id = ?2 AND rf.isActive")
    Optional<PaymentMethodRequiredFields> findByNameAndPaymentMethodId(String name, long id);
}
