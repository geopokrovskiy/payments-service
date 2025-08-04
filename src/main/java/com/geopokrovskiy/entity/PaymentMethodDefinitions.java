package com.geopokrovskiy.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "payment_method_definitions")
public class PaymentMethodDefinitions {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String currencyCode;

    @Column(name = "country_alpha3_code")
    private String countryAlpha3Code;

    @Column
    private boolean isAllCurrencies;

    @Column
    private boolean isAllCountries;

    @Column
    private boolean isActive;

    @OneToOne(mappedBy = "definitions")
    private PaymentMethod paymentMethod;
}
