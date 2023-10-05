package com.turkcell.spring.starter.controllers;

import com.turkcell.spring.starter.business.abstracts.CategoryService;
import com.turkcell.spring.starter.business.exceptions.BusinessException;
import com.turkcell.spring.starter.entities.Category;
import com.turkcell.spring.starter.entities.dtos.category.CategoryForAddDto;
import com.turkcell.spring.starter.entities.dtos.category.CategoryForListingDto;
import com.turkcell.spring.starter.entities.dtos.category.CategoryForUpdateDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoriesController {

    private final CategoryService categoryService;

    public CategoriesController(CategoryService categoryService) {

        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<CategoryForListingDto> getCategories() {
        List<CategoryForListingDto> categoriesInDb = categoryService.getAll();
        return categoriesInDb;
    }

    @GetMapping("getByName")
    public List<Category> getCategoriesByName(@RequestParam("name") String name) {
        //List<Category> categories = categoryRepository.findByCategoryNameContaining(name);
        return null;
    }

    @GetMapping("search")
    public List<Category> search(@RequestParam("name") String name) {
        //List<Category> categories = categoryRepository.searchNative(name);
        return null;
    }

    @GetMapping("getById")
    public Category getCategoryById(@RequestParam("id") int id) {
        //Category category = categoryRepository.findById(id).orElseThrow();
        return null;
    }

    @PostMapping()
    public ResponseEntity add(@RequestBody @Valid CategoryForAddDto request) {
        categoryService.add(request);
        return new ResponseEntity("Kategori eklendi", HttpStatus.CREATED);
    }

    @PutMapping()
    public void updateCategory(@RequestBody @Valid CategoryForUpdateDto updateDto) {
        categoryService.updateCategory(updateDto);

    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") int id) {
        categoryService.deleteCategory(id);
    }
}
