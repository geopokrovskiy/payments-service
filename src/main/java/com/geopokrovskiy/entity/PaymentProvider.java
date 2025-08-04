package com.geopokrovskiy.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "payment_provider")
public class PaymentProvider {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentProvider")
    private List<PaymentMethod> paymentMethods;
}
