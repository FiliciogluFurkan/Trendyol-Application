package com.example.trendyol.mapper;

import com.example.trendyol.dto.response.PrivilegeResponse;
import com.example.trendyol.model.rolesprivileges.PrivilegeModel;
import org.springframework.stereotype.Component;

@Component
public class PrivilegeMapper {
    public PrivilegeResponse toResponse(PrivilegeModel privilegeModel) {

        return PrivilegeResponse.builder()
                .name(privilegeModel.getName())
                .id(privilegeModel.getId())
                .build();
    }

}
