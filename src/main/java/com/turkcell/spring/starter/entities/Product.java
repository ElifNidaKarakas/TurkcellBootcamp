package com.turkcell.spring.starter.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Table(name = "products")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="product_name")
    private String name;

    @Column(name = "quantity_per_unit")
    private String quantity_per_unit;
    @Column(name = "unit_price")
    private float unit_price;
    @Column(name = "units_in_stock")
    private int units_in_stock;
    @Column(name = "units_on_order")
    private int  units_on_order;
    @Column(name = "reorder_level")
    private String reorder_level;
    @Column(name = "discontinued")
    private int discontinued;

    @ManyToOne() //birden çok ürün tek kategoride olabilir
    @JoinColumn(name = "category_id")
    @JsonIgnore //sonsuz döngüye girmemesi için
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

    @ManyToOne()
    @JoinColumn(name="supplier_id")
    private Supplier supplier;
}

// ORM => Object Relation Mapping
// Infinite recursion:sonsoz döngü