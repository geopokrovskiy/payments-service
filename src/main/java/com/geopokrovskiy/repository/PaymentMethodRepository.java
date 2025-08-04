package com.geopokrovskiy.repository;

import com.geopokrovskiy.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    @Query("SELECT pm FROM PaymentMethod pm WHERE pm.definitions.countryAlpha3Code = ?1 and pm.isActive = true")
    List<PaymentMethod> getAllByCountry(String country);

    @Query("SELECT pm FROM PaymentMethod pm WHERE pm.definitions.currencyCode = ?1 and pm.isActive = true ")
    List<PaymentMethod> getAllByCurrency(String currency);

    @Query("SELECT pm FROM PaymentMethod pm WHERE pm.definitions.countryAlpha3Code = ?1 and pm.definitions.currencyCode = ?2 " +
            "AND pm.isActive = true")
    List<PaymentMethod> getAllByCountryAndCurrency(String country, String currency);

    Optional<PaymentMethod> findById(Long id);

    Optional<PaymentMethod> findByName(String name);

}
