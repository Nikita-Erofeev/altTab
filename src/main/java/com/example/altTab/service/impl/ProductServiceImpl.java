package com.example.altTab.service.impl;

import com.example.altTab.exception.ResourceNotFoundException;
import com.example.altTab.model.Product;
import com.example.altTab.repository.ProductRepository;
import com.example.altTab.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Product> getAllProductsNotHidden() {
        return repository.getAllProductsNotHidden();
    }

    @Override
    public Product getProductById(Long id) {
//        Optional<Product> optionalProduct = repository.findById((long) id);
//        if(optionalProduct.isPresent()){
//            return optionalProduct.get();
//        } else {
//            throw new ResourceNotFoundException("Product ","id", id.toString());
//        }

        return repository.findById(id)
                .orElseThrow(
                        ()->new ResourceNotFoundException("Product ","id", id.toString())
                );

    }

    @Override
    public Product updateProduct(Product product, Long id) {
//        Проверяем, существует ли продукт с таким id
        Product existingProduct = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Product ","id", id.toString())
        );
        product.setId(id);
        repository.save(product);
        return product;
    }

    @Override
    public void deleteProductById(Long id) {
       repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Product ","id", id.toString())
        );
        repository.deleteById(id);
    }
}
