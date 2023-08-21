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
    @Column(name = "picture_uri")
    private String picturePath;

    @JsonView(Views.Public.class)
    @Column(name = "price", nullable = false)
    private int price;

    @JsonView(Views.Public.class)
    private String discount;

    @JsonView(Views.Public.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "discount_time", nullable = true)
    private LocalDateTime discountTime;

    @JsonView(Views.Public.class)
    @Column(name = "current_amount", nullable = false)
    private int currentAmount;

    @JsonView(Views.Internal.class)
    @Column(name = "is_hidden", nullable = false)
    private Boolean hidden = false;

    @JsonView(Views.PublicExtended.class)
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "ref_product_property",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "property_id")}
    )
    private List<Property> properties;

    public boolean isValid(){
        return !(name == null || name.length() <= 4 || hidden == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && currentAmount == product.currentAmount && Objects.equals(name, product.name) && Objects.equals(picturePath, product.picturePath) && Objects.equals(discount, product.discount) && Objects.equals(discountTime, product.discountTime) && Objects.equals(hidden, product.hidden) && Objects.equals(properties, product.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, picturePath, price, discount, discountTime, currentAmount, hidden, properties);
    }
}
