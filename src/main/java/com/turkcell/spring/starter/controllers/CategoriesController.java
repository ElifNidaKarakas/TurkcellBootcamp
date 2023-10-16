package com.turkcell.spring.starter.controllers;

import com.turkcell.spring.starter.business.abstracts.CategoryService;
import com.turkcell.spring.starter.entities.Category;
import com.turkcell.spring.starter.entities.dtos.category.CategoryForAddDto;
import com.turkcell.spring.starter.entities.dtos.category.CategoryForListingDto;
import com.turkcell.spring.starter.entities.dtos.category.CategoryForUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoriesController {

    private final CategoryService categoryService;
    private final MessageSource messageSource;

    @GetMapping()
    public List<CategoryForListingDto> getCategories() {
        //test commit
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
        return new ResponseEntity(messageSource.getMessage("categoryAdded", new Object[]{
                request.getCategoryName()}, LocaleContextHolder.getLocale()), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity updateCategory(@RequestBody @Valid CategoryForUpdateDto updateDto) {
        return new ResponseEntity(messageSource.getMessage("categoryUpdated", new Object[]{
                updateDto.getCategoryName()}, LocaleContextHolder.getLocale()), HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") int id) {

        return new ResponseEntity(messageSource.getMessage("categoryDeleted", new Object[]
                {id}, LocaleContextHolder.getLocale()), HttpStatus.OK);

    }
}
