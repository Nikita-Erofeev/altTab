package com.example.altTab.model.product;

import com.example.altTab.model.jsonviews.Views;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @JsonView(Views.Public.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonView(Views.Public.class)
    @Column(name = "name", nullable = false)
    private String name;

    @JsonView(Views.Public.class)
    @Column(name = "price", nullable = false)
    private int price;

    @JsonView(Views.Public.class)
    private String discount;

    @JsonView(Views.Public.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime discount_time;

    @JsonView(Views.Internal.class)
    private int current_amount;

    @JsonView(Views.Internal.class)
    private Boolean is_hidden;

//    @ManyToMany(fetch = FetchType.LAZY,
//        cascade = {
//            CascadeType.PERSIST, CascadeType.MERGE
//        })
//    @JoinTable(name = "ref_product_property",
//        joinColumns = {@JoinColumn(name = "product_id")},
//        inverseJoinColumns = {@JoinColumn(name = "property_id")})
//    private List<Property> propertyList = new ArrayList<>();

    @JsonView(Views.PublicExtended.class)
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<ProductPropertyValue> properties;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && price == product.price && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }
}
