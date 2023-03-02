package com.gk.order.service;


import com.gk.order.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="product-service")
public interface ProductService {
    @GetMapping("/product/{id}")
    Product product(@PathVariable("id") Long id);


}
