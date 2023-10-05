package com.turkcell.spring.starter.repositories;

import com.turkcell.spring.starter.entities.Category;
import com.turkcell.spring.starter.entities.dtos.category.CategoryForListingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    //DERIVED METHOTLAR
    List<Category> findByCategoryNameContaining(String categoryName);
    List<Category> findByDescription(String description);
    Category findByCategoryName(String categoryName);
    Category findById(int id);


    // Native SQL
    // JPQL => JPA'nın SQL'e neredeyse birebir benzer versiyonu..
    // JPQL => Tablo ismi yerine entity yazmak


    //JPQL
    @Query(value = "Select c FROM Category c WHERE c.categoryName LIKE %:categoryName%", nativeQuery = false)
    List<Category> search(String categoryName);

    //true-false JPQL olup olmadığını belirtir


    //NATIVE SQL
    @Query(value = "Select * from categories Where category_name LIKE %:categoryName%", nativeQuery = true)
    List<Category> searchNative(String categoryName);

    @Query(value="SELECT new " +
            "com.turkcell.spring.starter.entities.dtos.category.CategoryForListingDto(c.categoryId, c.categoryName, c.description) FROM Category c")
    List<CategoryForListingDto> getForListing();
}

//spring derived query methods