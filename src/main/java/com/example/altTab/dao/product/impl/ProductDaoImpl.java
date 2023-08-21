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
import org.springframework.transaction.annotation.Transactional;

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

    private void savePropsWithoutIdForProduct(Product product){
        if(product.getProperties() == null || product.getProperties().size() == 0) return;
        List<Property> newProps = product.getProperties().stream()
                .filter(property -> property.getId() < 1)
                .toList();
        if(newProps.size() > 0){
            product.getProperties().removeAll(newProps);
            product.getProperties().addAll(propertyRepository.saveAll(newProps));
        }
    }

    @Transactional
    @Override
    public Product saveProduct(Product product){
        try {
            savePropsWithoutIdForProduct(product);
            return productRepository.save(product);
        } catch (RuntimeException e){
            throw new BadRequestException("Can't save the product");
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

    @Transactional
    @Override
    public Product updateProduct(Product product, Long id) {
//        Проверяем, что точно существует продукт с таким id
        Product existingProduct = productRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Product ","id", id.toString())
        );
        try {
            savePropsWithoutIdForProduct(product);
            product.setId(id);
            productRepository.save(product);
        } catch (RuntimeException e) {
            throw new BadRequestException("Can't save the product");
        }
        return productRepository.findById(id).get();
    }

    @Transactional
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

    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

}
