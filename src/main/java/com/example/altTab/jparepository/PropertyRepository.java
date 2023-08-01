package com.example.altTab.jparepository;

import com.example.altTab.model.product.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;

public interface PropertyRepository  extends JpaRepository<Property, Long> {

    @Query("select p from Property p where p.name ilike ?1")
    List<Property> getPropertiesNamedLike(@NonNull String name);


}
