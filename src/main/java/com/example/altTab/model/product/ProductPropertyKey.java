package com.example.altTab.model.product;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class ProductPropertyKey implements Serializable {

    @Column(name = "product_id")
    Long productId;
    @Column(name = "property_id")
    Long propertyId;
}
