package com.example.altTab.repository;

import com.example.altTab.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.is_hidden = false")
    List<Product> getAllProductsNotHidden();

}
