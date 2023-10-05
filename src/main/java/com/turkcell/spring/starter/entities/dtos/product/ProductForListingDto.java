package com.turkcell.spring.starter.entities.dtos.product;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductForListingDto {

    private int id;
    private String name;
    private String quantity_per_unit;
    private float unit_price;
    private int units_in_stock;
    private int   units_on_order;
    private int discontinued;



}
