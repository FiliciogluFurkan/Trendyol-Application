package com.example.trendyol.repository;

import com.example.trendyol.model.postgres.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel,Long> {
    UserModel findByUserNameAndVisible(String username,Boolean status);
    UserModel findByUserName(String userName);
    boolean existsByUserName(String username);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByIdentityNumber(String identityNumber);


}
