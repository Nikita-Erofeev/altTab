package com.example.altTab.model.product;

import com.example.altTab.model.jsonviews.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "property")
public class Property {
    @JsonView(Views.PublicExtended.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @JsonView(Views.PublicExtended.class)
    @Column(name = "name", nullable = false)
    private String name;

    @JsonView(Views.PublicExtended.class)
    @Column(name = "value", nullable = false)
    private String value;

    @JsonView({Views.Internal.class})
    @Column(name = "is_hidden")
    private boolean hidden;

    @JsonIgnore
    @ManyToMany(mappedBy = "properties")
    @ToString.Exclude
    private List<Product> products;

    public boolean isValid(){
        return name != null && name.length() > 4 && value != null && !value.equals("");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return hidden == property.hidden && Objects.equals(name, property.name) && Objects.equals(products, property.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hidden, products);
    }
}
