package com.example.trendyol.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    private Integer age;
    private String name;
    private String email;
    private String surname;
    private String username;
    private String password;
    private String birthday;
    private String phoneNumber;
    private String identityNumber;
}
