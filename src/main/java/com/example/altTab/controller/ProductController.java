package com.example.altTab.controller;

import com.example.altTab.model.jsonviews.Views;
import com.example.altTab.model.product.Product;
import com.example.altTab.model.product.Property;
import com.example.altTab.service.ProductService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @JsonView(Views.Public.class) // без характеристик
    @GetMapping("")
    public List<Product> getAllProducts(){
        return productService.getAllProductsNotHidden();
    }

    @JsonView(Views.PublicExtended.class)
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK) ;
    }

    @GetMapping("/{id}/props")
    public ResponseEntity<List<Property>> getAllPropertiesByProductId(@PathVariable("id") Long id){
        return new ResponseEntity<>(productService.getAllPropertiesByProductId(id), HttpStatus.OK);
    }

    @GetMapping("/props")
    public ResponseEntity<List<Property>> getAllProperties(){
        return new ResponseEntity<>(productService.getAllProperties(), HttpStatus.OK);
    }

//    для админа, чтобы можно было управлять списком характеристик
//    todo переместить
    @GetMapping("/props/{name}")
    public ResponseEntity<List<Property>> getPropertiesNamedLike(@PathVariable("name") String propertyName){
        return new ResponseEntity<>(productService.getPropertiesNamedLike(propertyName), HttpStatus.OK);
    }

//    todo переместить
    @JsonView(Views.PublicExtended.class)
    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        log.info("/save " + product.toString());
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }

//    todo переместить
    @JsonView(Views.PublicExtended.class)
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product,
                                                 @PathVariable("id") Long id){
        return new ResponseEntity<>(productService.updateProduct(product, id), HttpStatus.OK);
    }

//    todo переместить
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id")Long id){
        productService.deleteProductById(id);
        return new ResponseEntity<>(String.format("Product with id= %d deleted successfully",id), HttpStatus.OK);
    }





}
