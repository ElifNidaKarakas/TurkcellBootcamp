package com.turkcell.spring.starter.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="customer_customer_demo")
public class CustomerCustomerDemo {
    @Id
    @Column(name="customer_id") //postgrede ki kolon ismi
    private String customerId;

    @Column(name="customer_type_id")
    private String customerTypeId;


}
