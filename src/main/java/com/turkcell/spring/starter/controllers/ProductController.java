package com.turkcell.spring.starter.controllers;

import com.turkcell.spring.starter.entities.Product;
import com.turkcell.spring.starter.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")

public class ProductController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @GetMapping
    public List<Product> getProducts() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @GetMapping("getById")
    public Product getById(@RequestParam("id") int id) {
        Product product = productRepository.findById(id).orElseThrow();
        return product;

    }

    @PostMapping()
    public ResponseEntity addProduct(@RequestBody Product product) {
        productRepository.save(product);
        return new ResponseEntity("ürün eklendi", HttpStatus.CREATED);

    }
}