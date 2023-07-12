package com.example.altTab.service.impl;

import com.example.altTab.jparepository.PropertyRepository;
import com.example.altTab.model.product.Property;
import com.example.altTab.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository repository;

    @Autowired
    public PropertyServiceImpl(PropertyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Property> getAllPropertiesByProductId(Long id) {
//        return repository.getAllPropertiesByProductId(productId);
        return null;
    }

}
