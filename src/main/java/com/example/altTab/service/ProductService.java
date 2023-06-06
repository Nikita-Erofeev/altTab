package com.example.altTab.service;

import com.example.altTab.model.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    List<Product> getAllProductsNotHidden();
    Product getProductById(Long id);

    Product updateProduct(Product product, Long id);
    void deleteProductById(Long id);
}
