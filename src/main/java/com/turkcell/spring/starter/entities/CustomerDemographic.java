package com.turkcell.spring.starter.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="customer_demographics")
public class CustomerDemographic {
    @Id
    @Column(name="customer_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String customerTypeId;

    @Column(name="customer_desc")
    private String customerDesc;
}
