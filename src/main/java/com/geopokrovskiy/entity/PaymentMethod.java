package com.geopokrovskiy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payment_method")
public class PaymentMethod {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String type;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime modifiedAt;

    @Column
    private String name;

    @Column
    private boolean isActive;

    @Column
    private String providerUniqueId;

    @Column
    private String providerMethodType;

    @Column
    private String logo;

    @Column
    private String profileType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_method_definitions_id", referencedColumnName = "id")
    private PaymentMethodDefinitions definitions;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentMethod")
    private List<PaymentMethodRequiredFields> requiredFields;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "provider_id", referencedColumnName = "id", nullable = false)
    private PaymentProvider paymentProvider;
}
