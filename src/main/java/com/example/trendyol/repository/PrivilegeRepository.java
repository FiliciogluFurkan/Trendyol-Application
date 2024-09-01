package com.example.trendyol.repository;

import com.example.trendyol.model.rolesprivileges.PrivilegeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<PrivilegeModel,Long> {

}
