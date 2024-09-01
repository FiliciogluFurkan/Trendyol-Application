package com.example.trendyol.repository;



import com.example.trendyol.model.mongo.BaseCategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BaseCategoryRepository extends JpaRepository<BaseCategoryModel,Long> {

    boolean existsByName(String name);
}
