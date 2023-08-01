package com.example.altTab.service;

import com.example.altTab.model.product.ProductPropertyValue;
import com.example.altTab.model.product.Property;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropertyService {
    List<Property> getPropertiesNamedLike(String propertyName);
}
