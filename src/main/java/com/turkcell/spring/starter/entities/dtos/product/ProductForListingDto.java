package com.turkcell.spring.starter.entities.dtos.product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductForListingDto {
    private int id;
    private String name;
    private String quantity_per_unit;
    private int unit_price;
    private int unit_in_stock;
    private  short units_on_order;
    private int discontinued;
}
