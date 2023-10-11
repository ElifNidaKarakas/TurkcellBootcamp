package com.turkcell.spring.starter.controllers;

import com.turkcell.spring.starter.business.abstracts.ProductService;
import com.turkcell.spring.starter.entities.Product;
import com.turkcell.spring.starter.entities.dtos.product.ProductForAddDto;
import com.turkcell.spring.starter.entities.dtos.product.ProductForListingDto;
import com.turkcell.spring.starter.entities.dtos.product.ProductForGetByIdDto;
import com.turkcell.spring.starter.entities.dtos.product.ProductForUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final MessageSource messageSource;

    @GetMapping()
    public List<ProductForListingDto> getProducts() {
        List<ProductForListingDto> productsInDb = productService.getAll();
        return productsInDb;
    }

    @GetMapping("/id")
    public List<ProductForGetByIdDto> getByIdProducts(int id) {
        List<ProductForGetByIdDto> productsInIdDb = productService.getById(id);
        return productsInIdDb;
    }


    @PostMapping()
    public ResponseEntity addProduct(@RequestBody @Valid ProductForAddDto request) {
        productService.add(request);
        return new ResponseEntity(messageSource.getMessage("ProductAdd", new Object[]{request.getName()}, LocaleContextHolder.getLocale()), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity updateProduct(@PathVariable int id, @RequestBody @Valid ProductForUpdateDto updateDto) {
        productService.updateProduct(id, updateDto);
        return new ResponseEntity(messageSource.getMessage("ProductUpdate", new Object[]{updateDto.getName()}, LocaleContextHolder.getLocale()), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return new ResponseEntity(messageSource.getMessage("ProductDelete", null, LocaleContextHolder.getLocale()), HttpStatus.OK);

    }
}