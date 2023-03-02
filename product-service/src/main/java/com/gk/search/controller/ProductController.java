package com.gk.search.controller;

import com.gk.search.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {


    static List<Product> db =new ArrayList<>();

    static {
        db.add(new Product(100L,"redmi",36000.0f,"electronics"));
        db.add(new Product(101L,"sony",16000.0f,"tv"));
        db.add(new Product(102L,"bed",9000.0f,"other"));
        db.add(new Product(103L,"bags",800.0f,"other"));
        db.add(new Product(104L,"pant",600.0f,"cloth"));
    }

    @GetMapping
    public List<Product> allProducts(){
        return db;
    }

    @GetMapping("/{id}")
    public Product product(@PathVariable("id") Long id){
        return db.get((int) (id-100));
    }
}
