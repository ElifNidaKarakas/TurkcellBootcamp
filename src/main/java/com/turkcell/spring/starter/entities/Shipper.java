package com.turkcell.spring.starter.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="shippers")
public class Shipper {
    @Id
    @Column(name="shipper_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shipperId;

    @Column(name="company_name")
    private String companyName;

    @Column(name="phone")
    private String phone;
}