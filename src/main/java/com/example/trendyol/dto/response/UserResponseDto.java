package com.example.trendyol.dto.response;

import com.example.trendyol.model.roles.RoleModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private Long Id;
    private Integer age;
    private String name;
    private String email;
    private String surname;
    private String username;
    private String birthday;
    private String phoneNumber;
    private String identityNumber;
    private Set<RoleModel> roleModels;
    private ZonedDateTime createDateTime;
    private ZonedDateTime updatedDateTime;

}
