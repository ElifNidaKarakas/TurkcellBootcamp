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
        categoryNameCanNotBeEmpty(request.getCategoryName());
        this.categoryShouldNotBeMoreThan10();
        categoryWithDescriptionLengthGreaterThanCategoryNameLength(request.getDescription(), request.getCategoryName());

        Category category = new Category();
        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());

        // Mapleme işlemi business içerisinde
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(int categoryId, CategoryForUpdateDto category) {
        this.checkIfCategoryExistById(categoryId);
        this.categoryShouldNotBeMoreThan10();
        categoryWithDescriptionLengthGreaterThanCategoryNameLength(category.getDescription(), category.getCategoryName());

        Category updateCategory = new Category();
        updateCategory.setCategoryId(category.getId());
        updateCategory.setCategoryName(category.getCategoryName());
        updateCategory.setDescription(category.getDescription());
        categoryRepository.save(updateCategory);

    }

    @Override
    public void deleteCategory(int categoryId) {
        this.checkIfCategoryExistById(categoryId);
        Category category = categoryRepository.findById(categoryId);
        categoryRepository.deleteById(categoryId);

    }


    private void categoryWithSameNameShouldNotExist(String categoryName) {
        Category categoryWithSameName = categoryRepository.findByCategoryName(categoryName);
        if (categoryWithSameName != null) {
            throw new BusinessException("Aynı kategori isminden 2 kategori bulunamaz.");
        }
    }

    private void categoryNameCanNotBeEmpty(String categoryName) {
        Category categoryNameEmpty = categoryRepository.findByCategoryName(categoryName);
        if (categoryNameEmpty == null) {
            throw new BusinessException("kategori adı boş olamaz");
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

    private void checkIfCategoryExistById(int id) {
        Category category = categoryRepository.findById(id);
        if (category == null) {
            throw new BusinessException("id değeri bulunamadı ");
        }


    }
}