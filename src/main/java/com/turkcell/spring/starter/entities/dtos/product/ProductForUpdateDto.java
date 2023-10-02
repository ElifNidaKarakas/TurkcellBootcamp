package com.turkcell.spring.starter.entities.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForUpdateDto {
    private int id;
    private String name;
    private String quantity_per_unit;
    private int unit_price;
    private int unit_in_stock;
    private  short units_on_order;

}
