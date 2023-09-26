package com.turkcell.spring.starter.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="orders")
public class Order {
    @Id
    @Column(name="order_id")
    private int orderId;
    @Column(name="customer_id")
    private String customerId;
    @Column(name="employee_id")
    private int employeeId;
    @Column(name="order_date")
    private LocalDate orderDate;
    @Column(name="required_date")
    private LocalDate requiredDate;
    @Column(name="shipped_date")
    private LocalDate shippedDate;
    @Column(name="ship_via")
    private int shipVia;
    @Column(name="freight")
    private String freight;
    @Column(name="ship_name")
    private String shipName;
    @Column(name="ship_address")
    private String shipAddress;
    @Column(name="ship_city")
    private String shipCity;
    @Column(name="ship_region")
    private String shipRegion;
    @Column(name="ship_postal_code")
    private String shipPostalCode;
    @Column(name="ship_country")
    private String shipCountry;
}