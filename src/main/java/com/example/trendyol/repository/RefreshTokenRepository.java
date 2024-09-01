package com.example.trendyol.repository;

import com.example.trendyol.model.postgres.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    RefreshToken findByUserId(Long id);

    void deleteByUserId(Long userId);
}
