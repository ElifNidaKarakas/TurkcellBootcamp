package com.turkcell.spring.starter.entities.dtos.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data // Getter+Setter
public class CategoryForAddDto {
    @NotBlank(message = "Kategori adı girmek zorunludur!")
    @Size(min=3)
    private String categoryName;
    @NotBlank(message = "Açıklama girmek zorunludur!")
    private String description;
}