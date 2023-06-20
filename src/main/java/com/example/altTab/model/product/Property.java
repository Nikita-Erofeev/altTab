package com.example.altTab.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "c_property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long propertyId;
    @Column(name = "name", nullable = false)
    private String name;

//    @Column(name = "property_value", table = "ref_product_property")
//    private String value;

//    @ManyToMany(fetch = FetchType.LAZY,
//        cascade = {
//            CascadeType.PERSIST, CascadeType.MERGE
//        }, mappedBy = "propertyList")
//    @JsonIgnore
//    @ToString.Exclude
//    private List<Product> productList = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY)
    private List<ProductPropertyValue> properties;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return propertyId == property.propertyId && Objects.equals(name, property.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(propertyId, name);
    }
}
