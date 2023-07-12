package com.example.altTab.jparepository;

import com.example.altTab.model.product.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository  extends JpaRepository<Property, Long> {

//    List<Property> getAllPropertiesByProductId(Long productId);
}
