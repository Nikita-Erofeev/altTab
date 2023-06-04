package com.example.altTab.controller;

import com.example.altTab.model.Product;
import com.example.altTab.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class GreetController {

    private ProductService productService;
    @Autowired
    public GreetController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/hello")
    public String greeting(@RequestParam(value="name", defaultValue = "World") String name){
        return "Hello, " + name;
    }

    @PostMapping("/saveProduct")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        log.info("/saveProduct " + product.toString());
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }
}
