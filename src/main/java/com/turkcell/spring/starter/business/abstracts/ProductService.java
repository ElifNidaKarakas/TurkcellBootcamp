package com.turkcell.spring.starter.business.abstracts;

import com.turkcell.spring.starter.entities.dtos.product.ProductForListingDto;
import com.turkcell.spring.starter.entities.dtos.product.ProductForGetByIdDto;

import java.util.List;

public interface ProductService {
    List<ProductForListingDto> getAll();
    List<ProductForGetByIdDto> getById(int id);

}
