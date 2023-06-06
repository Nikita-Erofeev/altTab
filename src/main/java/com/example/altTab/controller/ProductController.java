package com.example.altTab.controller;

import com.example.altTab.model.Product;
import com.example.altTab.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class ProductController {

    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/hello")
    public String greeting(@RequestParam(value="name", defaultValue = "World") String name){
        return "Hello, " + name;
    }

    @GetMapping("/allProducts")
    public List<Product> getAllProducts(){
        return productService.getAllProductsNotHidden();
    }

    @GetMapping("/product={id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK) ;
    }

    @PostMapping("/saveProduct")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        log.info("/saveProduct " + product.toString());
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }
    @PutMapping("/product={id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product,
                                                 @PathVariable("id") Long id){
        return new ResponseEntity<>(productService.updateProduct(product, id), HttpStatus.OK);
    }
    @DeleteMapping("product={id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id")Long id){
        productService.deleteProductById(id);
        return new ResponseEntity<>(String.format("Product with id= %d deleted successfully",id), HttpStatus.OK);
    }

}
