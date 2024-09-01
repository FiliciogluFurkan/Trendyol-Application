package com.example.trendyol.dto.response;

import com.example.trendyol.model.roles.RoleModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrivilegeResponse {

    private Long id;
    private String name;
}
