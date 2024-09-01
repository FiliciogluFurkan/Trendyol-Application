package com.example.trendyol.controller;


import com.example.trendyol.data.ApiPaths;
import com.example.trendyol.dto.response.ApiResponse;
import com.example.trendyol.dto.response.PrivilegeResponse;
import com.example.trendyol.dto.response.RoleResponse;
import com.example.trendyol.model.roles.RoleModel;
import com.example.trendyol.service.RoleService;
import com.example.trendyol.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.ROLE)
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/assign-role/{user-id}/{role-id}")
    public ResponseEntity<ApiResponse<RoleResponse>> assignRoleToUser(@PathVariable("user-id") Long userId, @PathVariable("role-id") Long roleId) {

        ApiResponse<RoleResponse> response = roleService.assignRoleToUser(userId, roleId);
        return response.isSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    @PostMapping("/assign-privileges/{role-id}/{privileges-id}")
    public ResponseEntity<ApiResponse<PrivilegeResponse>> assignPrivilegeToRole(@PathVariable("role-id") Long roleId, @PathVariable("privileges-id") Long privilegesId) {
        ApiResponse<PrivilegeResponse> response = roleService.assignPrivilegesToRole(roleId, privilegesId);
        return response.isSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RoleResponse>>> getAllRoles(){

        ApiResponse<List<RoleResponse>> response=roleService.geAllRoles();
        return response.isSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    @GetMapping("/privilege")
    public ResponseEntity<ApiResponse<List<PrivilegeResponse>>> getAllPrivileges(){

        ApiResponse<List<PrivilegeResponse>> response=roleService.geAllPrivileges();
        return response.isSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }





}
