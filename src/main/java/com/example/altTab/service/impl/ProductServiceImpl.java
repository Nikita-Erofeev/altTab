package com.example.altTab.service.impl;

import com.example.altTab.model.Product;
import com.example.altTab.repository.ProductRepository;
import com.example.altTab.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository){
        this.repository = repository;
    }

    @Override
    public Product saveProduct(Product product){
        return repository.save(product);
    }
}
