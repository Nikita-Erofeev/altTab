package com.example.altTab.model.product;

import com.example.altTab.model.jsonviews.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
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

    @JsonView(Views.PublicExtended.class)
    @ManyToOne
    @MapsId("propertyId")
    @JoinColumn(name = "property_id")
    Property property;

    @JsonView(Views.PublicExtended.class)
    @Column(name = "property_value")
    String value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductPropertyValue that = (ProductPropertyValue) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
