package com.example.altTab.service;

import com.example.altTab.model.product.Product;
import com.example.altTab.model.product.ProductPropertyValue;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    List<Product> getAllProductsNotHidden();
    Product getProductById(Long id);

    Product updateProduct(Product product, Long id);
    void deleteProductById(Long id);
//test
    List<ProductPropertyValue> getAllPropertiesByProductId(Long id);

    void addPropertiesToProduct(Long id, List<ProductPropertyValue> productPropertyValues);
}
