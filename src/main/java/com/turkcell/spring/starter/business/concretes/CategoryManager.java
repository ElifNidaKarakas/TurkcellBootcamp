package com.turkcell.spring.starter.business.concretes;

import com.turkcell.spring.starter.business.abstracts.CategoryService;
import com.turkcell.spring.starter.entities.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService {

    public void add(Category category) {
    }

    @Override
    public List<Category> getAll() {

        return null;
    }

    @Override
    public Category getById(int id) {

        return null;
    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void delete(int id) {

    }
}