package com.turkcell.spring.starter.entities.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductForGetByIdDto {


    private int id;
    private String name;
    private String quantity_per_unit;
    private float unit_price;
    private int units_in_stock;
    private int   units_on_order;

}
