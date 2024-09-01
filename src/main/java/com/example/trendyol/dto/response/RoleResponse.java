package com.example.trendyol.dto.response;

import com.example.trendyol.model.postgres.UserModel;
import com.example.trendyol.model.rolesprivileges.PrivilegeModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RoleResponse {

    private Long id;
    private String name;
    private Set<PrivilegeModel> privileges;
}
