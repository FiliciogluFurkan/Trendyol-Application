package com.example.trendyol.model.update;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    @Email
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.com$", message = "Email must be valid and end with .com")
    private String email;

    @Size(min = 3, max = 50, message = "username's length must be between 3-50 character")
    private String userName;

    @Size(min = 3, max = 50, message = "password length must be between 3-50 character")
    private String password;

    @Pattern(regexp = "05\\d{9}", message = "Phone number must start with 05 and be followed by 9 digits")
    private String phoneNumber;

}
