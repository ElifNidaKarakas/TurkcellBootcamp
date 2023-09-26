package com.turkcell.spring.starter.business.abstracts;

import com.turkcell.spring.starter.entities.Product;

import java.util.List;

public interface ProductService {
    void add(Product product);
    List<Product> getAll();
    Product getById(int id);
    void update (Product product);
    void delete(int id);
}
