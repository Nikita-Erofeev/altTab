package com.example.altTab.service.impl;

import com.example.altTab.dao.product.ProductDao;
import com.example.altTab.exception.BadRequestException;
import com.example.altTab.model.product.Product;
import com.example.altTab.model.product.Property;
import com.example.altTab.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao){
        this.productDao = productDao;
    }

    @Transactional
    @Override
    public Product saveProduct(Product product){
        if(!product.isValid()){
            throw new BadRequestException("Product is not valid");
        }
        product.setId(0);
        //        Сохраняем сначала продукт, потом его характеристики вместе с ним
        List<Property> properties = product.getProperties();
        if(properties == null || properties.size() == 0){
            return productDao.saveProduct(product);
        }
        product.setProperties(null);
        Product result = productDao.saveProduct(product);
//        Оставляем только валидные характеристики
        properties = properties.stream().filter(Property::isValid).collect(Collectors.toList());
        if(properties.size() > 0){
            result.setProperties(properties);
            productDao.saveProduct(product);
        }
        return result;
    }

    @Override
    public List<Product> getAllProductsNotHidden() {
        return productDao.getAllProductsNotHidden();
    }

    @Override
    public Product getProductById(Long id) {
        if(id < 0){
            throw new BadRequestException("Bad product id");
        }
        return productDao.getProductById(id);

    }

    @Override
    public Product updateProduct(Product product, Long id) {
        if(id < 0 || !product.isValid()){
            throw new BadRequestException("Bad request");
        }
        return productDao.updateProduct(product, id);
    }

    @Override
    public void deleteProductById(Long id) {
        if(id < 0){
            throw new BadRequestException("Bad product id");
        }
        productDao.deleteProductById(id);
    }

    @Override
    public List<Property> getAllPropertiesByProductId(Long id){
        return getProductById(id).getProperties();
    }

    @Override
    public List<Property> getPropertiesNamedLike(String propertyName) {
        return productDao.getPropertiesNamedLike("%" + propertyName + "%");
    }


}
