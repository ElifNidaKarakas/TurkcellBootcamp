package com.turkcell.spring.starter.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="shippers")
public class Shipper {
    @Id
    @Column(name="shipper_id")
    private int shipperId;

    @Column(name="company_name")
    private String companyName;

    @Column(name="phone")
    private String phone;
}