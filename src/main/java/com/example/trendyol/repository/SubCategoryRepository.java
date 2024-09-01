package com.example.trendyol.repository;

import com.example.trendyol.model.mongo.SubCategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategoryModel, Long> {
    boolean existsByName(String name);
}
