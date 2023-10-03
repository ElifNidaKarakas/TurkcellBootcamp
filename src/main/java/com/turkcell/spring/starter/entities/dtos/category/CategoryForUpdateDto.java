package com.turkcell.spring.starter.entities.dtos.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryForUpdateDto {
    @NotBlank(message = "Id girmek zorunludur!")
    private int id;

    @NotBlank(message = "Kategori adı girmek zorunludur!")
    @Size(min = 3)
    private String categoryName;

    @NotBlank(message = "Açıklama girmek zorunludur!")
    private String description;
}