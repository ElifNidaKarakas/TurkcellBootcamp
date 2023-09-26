package com.turkcell.spring.starter.business.abstracts;

import com.turkcell.spring.starter.entities.Category;

import java.util.List;

public interface CategoryService {
    void add(Category category);
    List<Category> getAll();
    Category getById(int id);
    void update (Category category);
    void delete(int id);
}
