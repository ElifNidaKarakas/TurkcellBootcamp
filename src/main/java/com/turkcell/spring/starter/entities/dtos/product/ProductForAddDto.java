package com.turkcell.spring.starter.entities.dtos.product;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForAddDto {
    @Size(min = 3, message = "{productNameShouldBeMinimum}")
    private String name;
    //private String quantity_per_unit;
    @NotNull(message = "{notNull}")
    @Min(value = 0, message = "Fiyat değeri 0'dan küçük olamaz")
    private float unit_price;

    @NotNull(message = "{notNull}")
    @PositiveOrZero(message = "Stoktaki ürün sayısı 0 veya daha büyük olmalıdır.")
    private int unit_in_stock;
    private String reorder_level;
    private int discontinued;
    @PositiveOrZero(message = "{zeroOrThan}")
    @NotNull(message = "{notNull}")
    private int supplier_id;

    @PositiveOrZero(message = "{zeroOrThan}")
    @NotNull(message = "{notNull}")
    private int category_id;
}