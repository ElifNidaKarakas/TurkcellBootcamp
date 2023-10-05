package com.turkcell.spring.starter.business.concretes;

import com.turkcell.spring.starter.business.abstracts.CategoryService;
import com.turkcell.spring.starter.business.exceptions.BusinessException;
import com.turkcell.spring.starter.entities.Category;
import com.turkcell.spring.starter.entities.dtos.category.CategoryForAddDto;
import com.turkcell.spring.starter.entities.dtos.category.CategoryForListingDto;
import com.turkcell.spring.starter.entities.dtos.category.CategoryForUpdateDto;
import com.turkcell.spring.starter.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryManager implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryManager(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryForListingDto> getAll() {
        // DTO => Data Transfer Object

/*   BAD PRACTICE !!
        List<Category> categories = categoryRepository.findAll();
        List<CategoryForListingDto> categoryForListingDtos = new ArrayList<>();

        // Mapleme
        for (Category c: categories) {
            CategoryForListingDto dto = new CategoryForListingDto();
            dto.setCategoryId(c.getCategoryId());
            dto.setCategoryName(c.getCategoryName());
            categoryForListingDtos.add(dto);
        }
*/
        return categoryRepository.getForListing();
    }

    @Override
    public void add(CategoryForAddDto request) {
        // Business Rule => Aynı isimde iki kategori olmamalı

        categoryWithSameNameShouldNotExist(request.getCategoryName());
        this.categoryShouldNotBeMoreThan10();
        categoryWithDescriptionLengthGreaterThanCategoryNameLength(request.getDescription(), request.getCategoryName());

        Category category = Category.builder().build();
        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());

        // Mapleme işlemi business içerisinde
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(CategoryForUpdateDto category) {
        Category updateCategory = returnCategoryByIdIfExists(category.getId());
        updateCategory.setCategoryName(category.getCategoryName());
        updateCategory.setDescription(category.getDescription());
        categoryRepository.save(updateCategory);

    }

    @Override
    public void deleteCategory(int categoryId) {
        Category category = returnCategoryByIdIfExists(categoryId);
        categoryRepository.delete(category);

    }

    private Category returnCategoryByIdIfExists(int categoryId) {
        Category categoryToDelete = categoryRepository.findById(categoryId);
        if (categoryToDelete == null)
            throw new BusinessException("Böyle bir kategori bulunamadı.");
        return categoryToDelete;
    }

    private void categoryWithSameNameShouldNotExist(String categoryName) {
        Category categoryWithSameName = categoryRepository.findByCategoryName(categoryName);
        if (categoryWithSameName != null) {
            throw new BusinessException("Aynı kategori isminden 2 kategori bulunamaz.");
        }
    }

    public void categoryWithDescriptionLengthGreaterThanCategoryNameLength(String description, String categoryName) {
        if (categoryName.length() > description.length()) {
            throw new BusinessException("Kategori ismi açıklamadan uzun olamaz.");
        }
    }

    public void categoryShouldNotBeMoreThan10() {
        List<CategoryForListingDto> category = categoryRepository.getForListing();
        if (category.size() >= 10) {
            throw new BusinessException("10'dan fazla kategori bulunamaz.");
        }

    }
}