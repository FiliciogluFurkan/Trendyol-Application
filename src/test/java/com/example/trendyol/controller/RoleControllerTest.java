package com.example.trendyol.controller;

import com.example.trendyol.dto.request.RefreshRequest;
import com.example.trendyol.dto.response.ApiResponse;
import com.example.trendyol.dto.response.PrivilegeResponse;
import com.example.trendyol.dto.response.RoleResponse;
import com.example.trendyol.service.RoleService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoleControllerTest {


    @Mock
    RoleService roleService;

    @InjectMocks
    RoleController underTest;

    @Test
    public void assignRoleToUser_isSuccess(){
        Long userId=1L;
        Long roleId=1L;

        ApiResponse<RoleResponse> expectedResponse=new ApiResponse<RoleResponse>();
        expectedResponse.setSuccess(true);

        when(roleService.assignRoleToUser(userId,roleId)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<RoleResponse>> expectedResult=ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<RoleResponse>> actualResult=underTest.assignRoleToUser(userId,roleId);

        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void assignRoleToUser_isFailure(){
        Long userId=1L;
        Long roleId=1L;

        ApiResponse<RoleResponse> expectedResponse=new ApiResponse<RoleResponse>();
        expectedResponse.setSuccess(false);

        when(roleService.assignRoleToUser(userId,roleId)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<RoleResponse>> expectedResult=ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
        ResponseEntity<ApiResponse<RoleResponse>> actualResult=underTest.assignRoleToUser(userId,roleId);

        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void assignPrivilegeToRole_isSuccess(){

        Long roleId=1L;
        Long privilegeId=1L;
        ApiResponse<PrivilegeResponse> expectedResponse=new ApiResponse<PrivilegeResponse>();
        expectedResponse.setSuccess(true);

        when(roleService.assignPrivilegesToRole(roleId,privilegeId)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<PrivilegeResponse>> expectedResult=ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<PrivilegeResponse>> actualResult=underTest.assignPrivilegeToRole(roleId,privilegeId);

        assertEquals(expectedResult,actualResult);


    }

    @Test
    public void assignPrivilegeToRole_isFailure(){

        Long roleId=1L;
        Long privilegeId=1L;
        ApiResponse<PrivilegeResponse> expectedResponse=new ApiResponse<PrivilegeResponse>();
        expectedResponse.setSuccess(false);

        when(roleService.assignPrivilegesToRole(roleId,privilegeId)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<PrivilegeResponse>> expectedResult=ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
        ResponseEntity<ApiResponse<PrivilegeResponse>> actualResult=underTest.assignPrivilegeToRole(roleId,privilegeId);

        assertEquals(expectedResult,actualResult);


    }


    @Test
    public void getAllRoles_isSuccess(){
        ApiResponse<List<RoleResponse>> expectedResponse=new ApiResponse<List<RoleResponse>>();
        expectedResponse.setSuccess(true);

        when(roleService.geAllRoles()).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<List<RoleResponse>>> expectedResult=ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<List<RoleResponse>>> actualResult=underTest.getAllRoles();

        assertEquals(expectedResult,actualResult);

    }

    @Test
    public void getAllRoles_isFailure(){
        ApiResponse<List<RoleResponse>> expectedResponse=new ApiResponse<List<RoleResponse>>();
        expectedResponse.setSuccess(false);

        when(roleService.geAllRoles()).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<List<RoleResponse>>> expectedResult=ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
        ResponseEntity<ApiResponse<List<RoleResponse>>> actualResult=underTest.getAllRoles();

        assertEquals(expectedResult,actualResult);

    }

    @Test
    public void getAllPrivileges_isSuccess(){

        ApiResponse<List<PrivilegeResponse>> expectedResponse=new ApiResponse<List<PrivilegeResponse>>();
        expectedResponse.setSuccess(true);

        when(roleService.geAllPrivileges()).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<List<PrivilegeResponse>>> expectedResult=ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<List<PrivilegeResponse>>> actualResult=underTest.getAllPrivileges();

        assertEquals(expectedResult,actualResult);

    }


    @Test
    public void getAllPrivileges_isFailure(){

        ApiResponse<List<PrivilegeResponse>> expectedResponse=new ApiResponse<List<PrivilegeResponse>>();
        expectedResponse.setSuccess(false);

        when(roleService.geAllPrivileges()).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<List<PrivilegeResponse>>> expectedResult=ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
        ResponseEntity<ApiResponse<List<PrivilegeResponse>>> actualResult=underTest.getAllPrivileges();

        assertEquals(expectedResult,actualResult);

    }

}
