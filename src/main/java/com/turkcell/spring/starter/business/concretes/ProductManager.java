package com.turkcell.spring.starter.business.concretes;

import com.turkcell.spring.starter.business.abstracts.ProductService;
import com.turkcell.spring.starter.entities.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {

    @Override
    public void add(Product product) {

    }

    @Override
    public List<Product> getAll() {

        return null;
    }

    @Override
    public Product getById(int id) {

        return null;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(int id) {

    }
}