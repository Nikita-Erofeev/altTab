package com.example.altTab.service;

import com.example.altTab.model.product.Property;

import java.util.List;

public interface PropertyService {
    List<Property> getAllPropertiesByProductId(Long id);
}
