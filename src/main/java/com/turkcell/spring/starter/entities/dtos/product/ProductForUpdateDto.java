package com.turkcell.spring.starter.entities.dtos.product;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForUpdateDto {
    private int id;

    @Size(min = 3, message = "{productNameShouldBeMinimum}")
    private String name;

    @Min(value = 1, message = "{minOne}")
    private String quantity_per_unit;

    @NotNull(message = "{notNull}")
    @PositiveOrZero(message = "{zeroOrThan}")
    private int unit_price;

    @PositiveOrZero(message = "{zeroOrThan}")
    private int unit_in_stock;

    private String reorder_level;

    @PositiveOrZero(message = "{zeroOrThan}")
    @NotNull(message = "{notNull}")
    private  int supplier_id;

    @PositiveOrZero(message = "{zeroOrThan}")
    @NotNull(message = "{notNull}")
    private int category_id;

}
