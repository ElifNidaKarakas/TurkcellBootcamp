package com.turkcell.spring.starter.entities.dtos.orderDetail;

import lombok.Data;

@Data
public class OrderDetailListingDto {
    private int orderId;
    private String productName;
}