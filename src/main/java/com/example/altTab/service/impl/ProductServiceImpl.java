package com.example.altTab.service.impl;

import com.example.altTab.exception.BadRequestException;
import com.example.altTab.exception.ResourceNotFoundException;
import com.example.altTab.jparepository.ProductRepository;
import com.example.altTab.jparepository.PropertyRepository;
import com.example.altTab.model.product.Product;
import com.example.altTab.model.product.ProductPropertyValue;
import com.example.altTab.service.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final PropertyRepository propertyRepository;
    private final EntityManager entityManager;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, PropertyRepository propertyRepository, EntityManager entityManager){
        this.productRepository = productRepository;
        this.propertyRepository = propertyRepository;
        this.entityManager = entityManager;
    }


//    Не изменяет характеристики продукта
    @Override
    public Product saveProduct(Product product){
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            throw new BadRequestException("A product with that name is already exists");
        }
    }

    @Override
    public List<Product> getAllProductsNotHidden() {
        return productRepository.getAllProductsNotHidden();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(
                        ()->new ResourceNotFoundException("Product ","id", id.toString())
                );

    }

    @Override
    public Product updateProduct(Product product, Long id) {
//        Проверяем, существует ли продукт с таким id
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

    @Override
    public List<ProductPropertyValue> getAllPropertiesByProductId(Long id){
        return getProductById(id).getProperties();
    }

    @Transactional
    @Override
    public void addPropertiesToProduct(Long id, List<ProductPropertyValue> productPropertyValues) {
//        If the product doesn't exist
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Product ","id", id.toString())
        );
//        except properties that the product already has
//        productPropertyValues = productPropertyValues.stream()
//                .filter(pr ->  !product.getProperties().contains(pr))
//                .collect(Collectors.toList());
//        product.getProperties().addAll(productPropertyValues);
//        try {
//            productRepository.save(product);
//        } catch (Exception e){
//            throw new BadRequestException("Can't save props");
//        }
        for (ProductPropertyValue ppv: productPropertyValues) {
            ppv.setProduct(product);
        }
        product.getProperties().addAll(productPropertyValues);
        product.getProperties().forEach(entityManager::persist);
        entityManager.persist(product);
    }
}
