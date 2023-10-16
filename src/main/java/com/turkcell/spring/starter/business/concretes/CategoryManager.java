package com.turkcell.spring.starter.business.concretes;

import com.turkcell.spring.starter.business.abstracts.CategoryService;
import com.turkcell.spring.starter.core.exceptions.BusinessException;
import com.turkcell.spring.starter.entities.Category;
import com.turkcell.spring.starter.entities.dtos.category.CategoryForAddDto;
import com.turkcell.spring.starter.entities.dtos.category.CategoryForListingDto;
import com.turkcell.spring.starter.entities.dtos.category.CategoryForUpdateDto;
import com.turkcell.spring.starter.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryManager implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final MessageSource messageSource;
    private final ModelMapper modelMapper;

    @Override
    public List<CategoryForListingDto> getAll() {
        return categoryRepository.getForListing();
    }

    @Override
    public void add(CategoryForAddDto request) {

        categoryWithSameNameShouldNotExist(request.getCategoryName());
        categoryShouldNotBeMoreThan10();
        categoryWithDescriptionLengthGreaterThanCategoryNameLength(request.getDescription(), request.getCategoryName());
        categoryNameCanNotBeEmpty(request.getCategoryName());

       /* Category category = Category.builder().build();
        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());

        categoryRepository.save(category);*/
        Category categoryFromAutoMapping = modelMapper.map(request, Category.class);
        categoryRepository.save(categoryFromAutoMapping);
    }

    @Override
    public void updateCategory(CategoryForUpdateDto category) {
        Category updateCategory = returnCategoryByIdIfExists(category.getId());
       /* updateCategory.setCategoryName(category.getCategoryName());
        updateCategory.setDescription(category.getDescription());
        categoryRepository.save(updateCategory);*/
        Category categoryFromAutoMapping = modelMapper.map(category, Category.class);
        categoryRepository.save(categoryFromAutoMapping);

    }

    @Override
    public void deleteCategory(int categoryId) {
        Category category = returnCategoryByIdIfExists(categoryId);
        categoryRepository.delete(category);

    }

    private Category returnCategoryByIdIfExists(int categoryId) {
        Category categoryToDelete = categoryRepository.findById(categoryId);
        if (categoryToDelete == null)
            throw new BusinessException(
                    messageSource.getMessage("categoryDoesNotExistWithGivenId", new Object[]{categoryId}, LocaleContextHolder.getLocale()));
        return categoryToDelete;
    }

    private void categoryWithSameNameShouldNotExist(String categoryName) {
        Category categoryWithSameName = categoryRepository.findByCategoryName(categoryName);
        if (categoryWithSameName != null) {
            throw new BusinessException(
                    messageSource.getMessage("categoryAllReady", null
                            , LocaleContextHolder.getLocale()));

        }
    }

    private void categoryNameCanNotBeEmpty(String categoryName) {
        Category categoryNameIsEmpty = categoryRepository.findByCategoryName(categoryName);
        if (categoryNameIsEmpty != null)
            throw new BusinessException
                    (messageSource.getMessage("categoryNotEmpty", null, LocaleContextHolder.getLocale()));
    }


    public void categoryWithDescriptionLengthGreaterThanCategoryNameLength(String description, String categoryName) {
        if (categoryName.length() > description.length()) {
            throw new BusinessException
                    (messageSource.getMessage("categoryNameLength", null, LocaleContextHolder.getLocale()));
        }

    }

    public void categoryShouldNotBeMoreThan10() {
        List<CategoryForListingDto> category = categoryRepository.getForListing();
        if (category.size() >= 10) {
            throw new BusinessException
                    (messageSource.getMessage("categorySize", null, LocaleContextHolder.getLocale()));
        }
    }
}