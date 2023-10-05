package com.turkcell.spring.starter.entities;


import jakarta.persistence.*; // * => ilgili paketin tüm alt paketlerini import eder.(ıd,column...)
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data // getter+setter
@Table(name="categories") //database deki tablo ismi
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @Column(name="category_id") //postgrede ki kolon ismi
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(name="category_name")
    private String categoryName;

    @Column(name="description")
    private String description;

    @OneToMany(mappedBy = "category") //product tablosunda verdiğimiz isim
    private List<Product> products;
//bir kategori de birden fazla ürün bulunduğu için listeleme yaparız
}