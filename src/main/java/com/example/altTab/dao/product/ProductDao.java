package com.example.altTab.dao.product;

import com.example.altTab.model.product.Product;
import com.example.altTab.model.product.Property;

import java.util.List;

public interface ProductDao {

    Product saveProduct(Product product);

    List<Product> getAllProductsNotHidden();

    Product getProductById(Long id);

    Product updateProduct(Product product, Long id);

    void deleteProductById(Long id);

    List<Property> getPropertiesNamedLike(String propertyName);

}
