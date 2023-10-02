package com.turkcell.spring.starter.controllers;

import com.turkcell.spring.starter.business.abstracts.ProductService;
import com.turkcell.spring.starter.entities.Product;
import com.turkcell.spring.starter.entities.dtos.product.ProductForListingDto;
import com.turkcell.spring.starter.entities.dtos.product.ProductForGetByIdDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
        private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<ProductForListingDto> getProducts()
    {
        List<ProductForListingDto> productsInDb = productService.getAll();
        return productsInDb;
    }

    @GetMapping("/id")
    public List<ProductForGetByIdDto> getByIdProducts(int id)
    {
        List<ProductForGetByIdDto> productsInIdDb = productService.getById(id);
        return productsInIdDb;
    }

    @GetMapping("getById")
    public Product getById(@RequestParam("id") int id) {
        //Product product = productRepository.findById(id).orElseThrow();
        return null;

    }

    @PostMapping()
    public ResponseEntity addProduct(@RequestBody Product product) {
      //  productRepository.save(product);
        return new ResponseEntity("ürün eklendi", HttpStatus.CREATED);

    }
}