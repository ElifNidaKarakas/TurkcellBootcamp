package com.turkcell.spring.starter.entities.dtos.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderForUpdateDto {
    @NotBlank(message = "güncelleme yapabilmek için id alanını boş bırakmayınız ")
    private int id;

    private LocalDate orderDate;
    private LocalDate requiredDate;
    private LocalDate shippedDate;
    private int shipVia;
    private String freight;

    @NotBlank(message = "alıcı isim alanı boş bırakılamaz")
    @Size(min = 3, max = 50)
    private String shipName;

    @NotBlank(message = "Adres alanı boş bırakılamaz")
    @Size(min = 5)
    private String shipAddress;

    @NotBlank(message = "Şehir alanı boş bırakılamaz")
    private String shipCity;

    @NotBlank(message = "Bölge alanı boş bırakılamaz")
    private String shipRegion;

    private String shipPostalCode;

    @NotBlank(message = "Ülke  alanı boş bırakılamaz")
    private String shipCountry;

}