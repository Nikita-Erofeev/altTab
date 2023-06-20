package com.example.altTab.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ref_product_property")
public class ProductPropertyValue {

    @JsonIgnore
    @EmbeddedId
    ProductPropertyKey id;

    @JsonIgnore
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne
    @MapsId("propertyId")
    @JoinColumn(name = "property_id")
    Property property;

    @Column(name = "property_value")
    String value;
}
