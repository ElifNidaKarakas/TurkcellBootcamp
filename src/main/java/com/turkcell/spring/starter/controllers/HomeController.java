package com.turkcell.spring.starter.controllers;

import com.turkcell.spring.starter.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("home")
@RequiredArgsConstructor
public class HomeController {
    private final MessageSource messageSource;
    List<Product> productList = new ArrayList<>();

    // http://localhost:8080/home GET İSTEĞİ
    @GetMapping("")
    public String get(){

        return messageSource.getMessage("hello", null, LocaleContextHolder.getLocale());
    }

    // http://localhost:8080/home POST İSTEĞİ
    @PostMapping("")
    public String getPost(){
        return "Merhaba Turkcell Post";
    }

    //http://localhost:8080/home/index
    @GetMapping("index")
    public String get2(){
        return "Merhaba Turkcell 2";
    }

    @GetMapping("products")
    public List<Product> getProducts(){

        return productList;
    }

    // Query String =>  localhost:8080/home/getById?id=1&name=deneme
    // Route => localhost:8080/home/getById/1/deneme
    @GetMapping("getById")
    public Product getById(@RequestParam("id") int id){
        Product product = new Product();
        product.setId(id);
        product.setName("Laptop");
        return product;
    }

    // Body => localhost:8080/product
    @PostMapping("product")
    public ResponseEntity addProduct(@RequestBody Product product){

        // built-in => hali hazırda mevcut
        if(product.getId() <= 0){
            //ResponseEntity:ilk parametre olarak göndereceği cevabı alan ,hangi http statusu istiyorsak onu yazarız bad:400
            return new ResponseEntity<>("Eklenecek ürünün idsi 0'dan büyük olmalıdır.", HttpStatus.BAD_REQUEST);
        }

        productList.add(product);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Product-Name",product.getName());
        return new ResponseEntity( product.getName() + " ürünü eklendi", headers,HttpStatus.CREATED);
    }


    // CREATED
    // 2XX => Başarılı => 200,201
    // 4XX => Başarısız  => 404,401


    // Her bir temel entitynin kendi controllerinin bulunması best practicedir.
}