package com.example.trendyol.repository;

import com.example.trendyol.model.postgres.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    List<ProductModel> findByNameContainingIgnoreCase(String name);
    boolean existsByName(String name);

}
