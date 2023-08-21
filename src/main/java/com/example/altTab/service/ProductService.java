package com.example.altTab.service;

import com.example.altTab.model.product.Product;
import com.example.altTab.model.product.Property;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    List<Product> getAllProductsNotHidden();
    Product getProductById(Long id);
    Product updateProduct(Product product, Long id);
    void deleteProductById(Long id);
    List<Property> getAllPropertiesByProductId(Long id);

    List<Property> getAllProperties();

    List<Property> getPropertiesNamedLike(String propertyName);

}
