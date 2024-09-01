package com.example.trendyol.repository;

import com.example.trendyol.model.postgres.BasketModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasketRepository extends JpaRepository<BasketModel,Long> {
    Optional<BasketModel> findByUserId(Long userId);
}
