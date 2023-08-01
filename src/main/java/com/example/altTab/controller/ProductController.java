package com.example.altTab.controller;

import com.example.altTab.model.jsonviews.Views;
import com.example.altTab.model.product.Product;
import com.example.altTab.model.product.ProductPropertyValue;
import com.example.altTab.model.product.Property;
import com.example.altTab.service.ProductService;
import com.example.altTab.service.PropertyService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@Slf4j
public class ProductController {

//    TODO определить, что делать с передачей null

    private final ProductService productService;
    private final PropertyService propertyService;
    @Autowired
    public ProductController(ProductService productService, PropertyService propertyService){
        this.productService = productService;
        this.propertyService = propertyService;
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

    @JsonView(Views.PublicExtended.class)
    @PostMapping("/{id}/addProps")
    public ResponseEntity<Product> addPropertiesToProduct(@PathVariable("id") Long productId,
                                                          @RequestBody List<ProductPropertyValue> productPropertyValues){
        productService.addPropertiesToProduct(productId, productPropertyValues);
        //        а если исключение?
        return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
    }

    @GetMapping("/{id}/props")
    public ResponseEntity<List<ProductPropertyValue>> getAllPropertiesByProductId(@PathVariable("id") Long id){
        return new ResponseEntity<>(productService.getAllPropertiesByProductId(id), HttpStatus.OK);
    }

//    для админа, чтобы можно было управлять списком характеристик
//    todo переместить
    @GetMapping("/admin/props/{name}")
    public ResponseEntity<List<Property>> getPropertiesNamedLike(@PathVariable("name") String propertyName){
        return new ResponseEntity<>(propertyService.getPropertiesNamedLike(propertyName), HttpStatus.OK);
    }

    @JsonView(Views.PublicExtended.class)
    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        log.info("/save " + product.toString());
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    @JsonView(Views.PublicExtended.class)
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product,
                                                 @PathVariable("id") Long id){
        return new ResponseEntity<>(productService.updateProduct(product, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id")Long id){
        productService.deleteProductById(id);
        return new ResponseEntity<>(String.format("Product with id= %d deleted successfully",id), HttpStatus.OK);
    }



}
