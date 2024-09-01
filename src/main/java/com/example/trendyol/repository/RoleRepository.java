package com.example.trendyol.repository;

import com.example.trendyol.model.roles.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleModel,Long> {
}
