package com.example.trendyol.model.postgres;

import com.example.trendyol.model.abstracts.BaseEntity;
import com.example.trendyol.model.roles.RoleModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;


@Entity
@SuperBuilder
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel extends BaseEntity {

    @NotBlank
    @Size(min = 3, max = 50, message = "name's length must be 3 and 50 characters")
    @Column(name = "user_name")
    private String name;


    @NotBlank
    @Size(min = 3, max = 50, message = "surname's length must be 3 and 50 characters")
    @Column(name = "user_surname")
    private String surname;

    @NotBlank
    @Size(min = 3, max = 50, message = "username's length must be 3 and 50 characters")
    @Column(name = "user_username")
    private String userName;


    @NotBlank
    // @Size(min = 3,max = 50,message = "password's length must be 3 and 50 characters")
    @Column(name = "user_password")
    private String password;


    @Positive(message = "age must be greater than 0")
    @Column(name = "user_Age")
    @NotNull
    private Integer age;

    @Pattern(regexp = "\\d{11}", message = "Identity number's length must be 11 digits")
    @Column(name = "identity_number")
    private String identityNumber;


    @NotBlank
    @Pattern(regexp = "05\\d{9}", message = "Phone number must start with 05 and be followed by 9 digits")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Email
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.com$", message = "Email must be valid and end with .com")
    @Column(name = "email", unique = false)
    private String email;


    @NotBlank(message = "Birth day cannot be null")
    @Column(name = "birth_day")
    private String birthday;

    @Column(name = "is_visible")
    private boolean visible;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<RoleModel> roles;

}
