package com.turkcell.spring.starter.business.abstracts;

import com.turkcell.spring.starter.entities.dtos.category.CategoryForAddDto;
import com.turkcell.spring.starter.entities.dtos.category.CategoryForListingDto;
import com.turkcell.spring.starter.entities.dtos.category.CategoryForUpdateDto;

import java.util.List;

public interface CategoryService {
    // Serviceler entityleri değil dtoları kullanmalı..
    List<CategoryForListingDto> getAll();

    void add(CategoryForAddDto request);

    void updateCategory(CategoryForUpdateDto category);

    void deleteCategory(int categoryId);
}