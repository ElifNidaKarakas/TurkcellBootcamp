package com.turkcell.spring.starter.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="customer_demographics")
public class CustomerDemographic {
    @Id
    @Column(name="customer_type_id")
    private String customerTypeId;

    @Column(name="customer_desc")
    private String customerDesc;
}
