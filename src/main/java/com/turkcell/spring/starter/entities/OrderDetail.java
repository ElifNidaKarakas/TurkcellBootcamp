package com.turkcell.spring.starter.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="order_details")
public class OrderDetail {
    @Id
    @Column(name="order_id")
    private int orderId;

    @Column(name="product_id")
    private int productId;

    @Column(name="unit_price")
    private int unitPrice;

    @Column(name="quantity")
    private int quantity;

    @Column(name="discount")
    private int discount;
}
