package com.turkcell.spring.starter.entities.dtos.product;

import lombok.Data;

@Data
public class ProductForAddDto {
    private String name;
    private int supplier_id;
    private  int category_id;
    private String quantity_per_unit;
    private int unit_price;
    private int unit_in_stock;
    private String reorder_level;
}
