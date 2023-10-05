package com.turkcell.spring.starter.entities.dtos.product;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForUpdateDto {
    @NotBlank(message = "id alanı boş bırakılamaz!")
    private int id;

    @NotBlank(message = "İsim alanı boş bırakılamaz!")
    @Size(min = 3, max = 50)
    private String name;


    private String quantity_per_unit;

    @NotNull(message = "Fiyat bilgisi boş geçilemez")
    @Min(value = 0, message = "Fiyat değeri 0'dan küçük olamaz")
    private int unit_price;

    @PositiveOrZero(message = "Stoktaki ürün sayısı 0 veya daha büyük olmalıdır.")
    private int unit_in_stock;

    private String reorder_level;

    @NotBlank(message = "supplier_id boş bırakılamaz")
    @Min(1)
    private  int supplier_id;

    @NotBlank(message = "category_id boş bırakılamaz")
    @Min(1)
    private int category_id;

}
