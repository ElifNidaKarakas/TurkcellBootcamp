package com.turkcell.spring.starter.repositories;

import com.turkcell.spring.starter.entities.Product;
import com.turkcell.spring.starter.entities.dtos.product.ProductForListingDto;
import com.turkcell.spring.starter.entities.dtos.product.ProductForGetByIdDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query(value="SELECT new " +
            "com.turkcell.spring.starter.entities.dtos.product.ProductForListingDto(p.id,p.name,p.quantity_per_unit,p.unit_price,p.unit_in_stock,p.units_on_order,p.discontinued) FROM Product p")
    List<ProductForListingDto> getForListing();

    @Query(value="SELECT new " +
            "com.turkcell.spring.starter.entities.dtos.product.ProductForGetByIdDto(p.id,p.name,p.quantity_per_unit,p.unit_price,p.unit_in_stock,p.units_on_order) FROM Product p")
    List<ProductForGetByIdDto> getForIdListing();
}
