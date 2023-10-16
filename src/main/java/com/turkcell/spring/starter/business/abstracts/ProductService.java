package com.turkcell.spring.starter.business.abstracts;

import com.turkcell.spring.starter.entities.dtos.product.ProductForAddDto;
import com.turkcell.spring.starter.entities.dtos.product.ProductForGetByIdDto;
import com.turkcell.spring.starter.entities.dtos.product.ProductForListingDto;
import com.turkcell.spring.starter.entities.dtos.product.ProductForUpdateDto;

import java.util.List;

public interface ProductService {
    List<ProductForListingDto> getAll();
    List<ProductForGetByIdDto> getById(int id);
    void add(ProductForAddDto request);
    void updateProduct(int productId, ProductForUpdateDto product);
    void deleteProduct(int id);
    float getByUnitPrice(int id);
}