package com.turkcell.spring.starter.entities.dtos.orderDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailForAddDto {

    private int productId;
    private int quantity;

}
