package com.turkcell.spring.starter.repositories;

import com.turkcell.spring.starter.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    //DERIVED METHOTLAR
    List<Category> findByCategoryName(String categoryName); //isme göre bul
    List<Category> findByCategoryNameContaining(String categoryName);
    List<Category> findByDescription(String description);



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
}

//spring derived query methods