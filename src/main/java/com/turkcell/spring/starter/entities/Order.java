package com.turkcell.spring.starter.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @ManyToMany()
    @JoinColumn(name = "customer_id")
    private List<Customer> customer;

    @ManyToMany()
    @JoinColumn(name = "employee_id")
    private List<Employee> employee;

    @Column(name = "order_date")
    private LocalDate orderDate;
    @Column(name = "required_date")
    private LocalDate requiredDate;
    @Column(name = "shipped_date")
    private LocalDate shippedDate;
    @Column(name = "ship_via")
    private int shipVia;
    @Column(name = "freight")
    private String freight;
    @Column(name = "ship_name")
    private String shipName;
    @Column(name = "ship_address")
    private String shipAddress;
    @Column(name = "ship_city")
    private String shipCity;
    @Column(name = "ship_region")
    private String shipRegion;
    @Column(name = "ship_postal_code")
    private String shipPostalCode;
    @Column(name = "ship_country")
    private String shipCountry;
}