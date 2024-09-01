package com.example.trendyol.service;

import com.example.trendyol.dto.response.ApiResponse;
import com.example.trendyol.dto.response.PrivilegeResponse;
import com.example.trendyol.dto.response.RoleResponse;
import com.example.trendyol.mapper.PrivilegeMapper;
import com.example.trendyol.mapper.RoleMapper;
import com.example.trendyol.model.postgres.UserModel;
import com.example.trendyol.model.roles.RoleModel;
import com.example.trendyol.model.rolesprivileges.PrivilegeModel;
import com.example.trendyol.repository.PrivilegeRepository;
import com.example.trendyol.repository.RoleRepository;
import com.example.trendyol.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.example.trendyol.validation.ValidationMessages.*;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final UserRepository userRepository;
    private final RoleMapper roleMapper;
    private final PrivilegeMapper privilegeMapper;
    private static final Logger log = LoggerFactory.getLogger(RoleService.class);


    @Transactional
    public ApiResponse<RoleResponse> assignRoleToUser(Long userId, Long roleId) {


        UserModel userModel = userRepository.findById(userId).orElse(null);
        if (userModel == null) {
            log.error("User not found with id {}", userId);
            return ApiResponse.failure(USER_NOT_FOUND);
        }

        RoleModel role = roleRepository.findById(roleId).orElse(null);
        if (role == null) {
            log.error("Role not found with id {}", roleId);
            return ApiResponse.failure(ROLE_NOT_FOUND);
        }

        Set<RoleModel> roles = userModel.getRoles();
        roles.add(role);
        userModel.setRoles(roles);
        log.info("Saving user: {}", userModel);
        userRepository.save(userModel);

        return ApiResponse.success(ROLE_ASSIGNED_SUCCESFULLY, roleMapper.toResponse(role));
    }

    @Transactional
    public ApiResponse<PrivilegeResponse> assignPrivilegesToRole(Long roleId, Long privilegeId) {

        RoleModel roleModel = roleRepository.findById(roleId).orElse(null);

        if (roleModel == null) {
            log.error("Role not found with id {}", roleId);
            return ApiResponse.failure(ROLE_NOT_FOUND);
        }

        PrivilegeModel privilegeModel = privilegeRepository.findById(privilegeId).orElse(null);

        if (privilegeModel == null) {
            log.error("Privilege not found with id {}", privilegeId);
            return ApiResponse.failure(PRIVILEGE_NOT_FOUND);
        }

        Set<PrivilegeModel> privileges = roleModel.getPrivileges();
        privileges.add(privilegeModel);
        roleModel.setPrivileges(privileges);
        roleRepository.save(roleModel);

        return ApiResponse.success(PRIVILEGE_ASSIGNED_SUCCESFULLY, privilegeMapper.toResponse(privilegeModel));
    }

    public ApiResponse<List<RoleResponse>> geAllRoles() {

        List<RoleResponse> roleResponseList = roleRepository.findAll()
                .stream().map(roleModel -> roleMapper.toResponse(roleModel)).toList();


        return ApiResponse.success(ROLES_RETRIEVED_SUCCESFULLY, roleResponseList);
    }


    public ApiResponse<List<PrivilegeResponse>> geAllPrivileges() {

        List<PrivilegeResponse> privilegeResponseList =privilegeRepository.findAll()
                .stream().map(privilegeModel -> privilegeMapper.toResponse(privilegeModel)).toList();

        return ApiResponse.success(PRIVILEGES_RETRIEVED_SUCCESFULLY,privilegeResponseList);
    }


}
