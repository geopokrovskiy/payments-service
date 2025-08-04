package com.geopokrovskiy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payment_method_required_fields")
public class PaymentMethodRequiredFields {

    @Id
    private UUID uid;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime modifiedAt;

    @Column
    private String paymentType;

    @Column(name = "country_alpha3_code")
    private String countryAlpha3Code;

    @Column
    private String name;

    @Column
    private String dataType;

    @Column
    private String validationType;

    @Column
    private String validationRule;

    @Column
    private String defaultValue;

    @Column
    private String valuesOptions;

    @Column
    private String description;

    @Column
    private String placeholder;

    @Column
    private String representationName;

    @Column
    private String language;

    @Column
    private boolean isActive;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;
}
