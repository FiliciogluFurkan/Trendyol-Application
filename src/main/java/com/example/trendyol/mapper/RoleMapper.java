package com.example.trendyol.mapper;

import com.example.trendyol.dto.response.RoleResponse;
import com.example.trendyol.model.roles.RoleModel;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleResponse toResponse(RoleModel roleModel){
        return RoleResponse.builder()
                .name(roleModel.getName())
                .id(roleModel.getId())
                .privileges(roleModel.getPrivileges())
                .build();

    }
}
