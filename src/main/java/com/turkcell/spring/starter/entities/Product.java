package com.turkcell.spring.starter.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "products")
@Entity
public class Product {
    @Id
    @Column(name = "product_id")
    private int id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "supplier_id")
    private int supplier_id;

    @Column(name = "quantity_per_unit")
    private String quantity_per_unit;

    @Column(name = "unit_price")
    private int unit_price;

    @Column(name = "unit_in_stock")
    private int unit_in_stock;

    @Column(name = "units_on_order")
    private  short units_on_order;

    @Column(name = "reorder_level")
    private String reorder_level;

    @Column(name = "discontinued")
    private int discontinued;

    @ManyToOne() //birden çok ürün tek kategoride olabilir
    @JoinColumn(name = "category_id")
    @JsonIgnore //sonsuz döngüye girmemesi için
    private Category category;
}

// ORM => Object Relation Mapping
// Infinite recursion:sonsoz döngü