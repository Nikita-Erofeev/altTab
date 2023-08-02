package com.example.altTab.dao.product.impl;

import com.example.altTab.dao.product.ProductDao;
import com.example.altTab.exception.BadRequestException;
import com.example.altTab.exception.ResourceNotFoundException;
import com.example.altTab.jparepository.ProductRepository;
import com.example.altTab.jparepository.PropertyRepository;
import com.example.altTab.model.product.Product;
import com.example.altTab.model.product.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDaoImpl implements ProductDao {

    private final ProductRepository productRepository;
    private final PropertyRepository propertyRepository;

    @Autowired
    public ProductDaoImpl(ProductRepository productRepository, PropertyRepository propertyRepository){
        this.productRepository = productRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public Product saveProduct(Product product){
        try {
            return productRepository.save(product);
        } catch (RuntimeException e){
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public List<Product> getAllProductsNotHidden() {
        return productRepository.getAllProductsNotHidden();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product ","id", id.toString())
        );
    }

    @Override
    public Product updateProduct(Product product, Long id) {
//        Проверяем, что точно существует продукт с таким id
        Product existingProduct = productRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Product ","id", id.toString())
        );
        product.setId(id);
        productRepository.save(product);
        return product;
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Product ","id", id.toString())
        );
        productRepository.deleteById(id);
    }

    public List<Property> getPropertiesNamedLike(String propertyName){
        return propertyRepository.getPropertiesNamedLike(propertyName);
    }

}
