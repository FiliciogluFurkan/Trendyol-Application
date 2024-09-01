package com.example.trendyol.controller;

import com.example.trendyol.dto.response.ApiResponse;
import com.example.trendyol.dto.response.ProductResponseDto;
import com.example.trendyol.dto.response.UserResponseDto;
import com.example.trendyol.model.update.UserUpdateRequest;
import com.example.trendyol.service.UserService;
import org.apache.catalina.User;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController underTest;

    @Test
    public void deleteUser_isSuccess() {
        Long userId = 1L;
        ApiResponse<Void> expectedResponse = new ApiResponse<Void>();
        expectedResponse.setSuccess(true);

        when(userService.deleteUser(userId)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<Void>> expectedResult = ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<Void>> actualResult = underTest.deleteUser(userId);

        assertEquals(expectedResult, actualResult);

    }


    @Test
    public void deleteUser_isFailure() {
        Long userId = 1L;
        ApiResponse<Void> expectedResponse = new ApiResponse<Void>();
        expectedResponse.setSuccess(false);

        when(userService.deleteUser(userId)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<Void>> expectedResult = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
        ResponseEntity<ApiResponse<Void>> actualResult = underTest.deleteUser(userId);

        assertEquals(expectedResult, actualResult);

    }


    @Test
    public void getUser_isSuccess() {
        Long userId = 1L;
        ApiResponse<UserResponseDto> expectedResponse = new ApiResponse<UserResponseDto>();
        expectedResponse.setSuccess(true);

        when(userService.getUser(userId)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<UserResponseDto>> expectedResult = ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<UserResponseDto>> actualResult = underTest.getUser(userId);

        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void getUser_isFailure() {
        Long userId = 1L;
        ApiResponse<UserResponseDto> expectedResponse = new ApiResponse<UserResponseDto>();
        expectedResponse.setSuccess(false);

        when(userService.getUser(userId)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<UserResponseDto>> expectedResult = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
        ResponseEntity<ApiResponse<UserResponseDto>> actualResult = underTest.getUser(userId);

        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void getAllUsers_isSuccess() {

        ApiResponse<List<UserResponseDto>> expectedResponse = new ApiResponse<List<UserResponseDto>>();
        expectedResponse.setSuccess(true);

        when(userService.getUsers()).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<List<UserResponseDto>>> expectedResult = ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<List<UserResponseDto>>> actualResult = underTest.getUsers();

        assertEquals(expectedResult, actualResult);


    }

    @Test
    public void getAllUsers_isFailure() {

        ApiResponse<List<UserResponseDto>> expectedResponse = new ApiResponse<List<UserResponseDto>>();
        expectedResponse.setSuccess(false);

        when(userService.getUsers()).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<List<UserResponseDto>>> expectedResult = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
        ResponseEntity<ApiResponse<List<UserResponseDto>>> actualResult = underTest.getUsers();

        assertEquals(expectedResult, actualResult);


    }

    @Test
    public void updateUser_isSuccess() {

        Long userId = 1L;
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        ApiResponse<UserResponseDto> expectedResponse = new ApiResponse<UserResponseDto>();
        expectedResponse.setSuccess(true);

        when(userService.updateUser(userId,userUpdateRequest)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<UserResponseDto>> expectedResult=ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<UserResponseDto>> actualResult=underTest.updateUser(userId,userUpdateRequest);

        assertEquals(expectedResult,actualResult);

    }

    @Test
    public void updateUser_isFailure() {

        Long userId = 1L;
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        ApiResponse<UserResponseDto> expectedResponse = new ApiResponse<UserResponseDto>();
        expectedResponse.setSuccess(false);

        when(userService.updateUser(userId,userUpdateRequest)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<UserResponseDto>> expectedResult=ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
        ResponseEntity<ApiResponse<UserResponseDto>> actualResult=underTest.updateUser(userId,userUpdateRequest);

        assertEquals(expectedResult,actualResult);

    }

    @Test
    public void getProductsOfUser_isSuccess(){

        Long userId=1L;
        ApiResponse<List<ProductResponseDto>> expectedResponse=new ApiResponse<List<ProductResponseDto>>();
        expectedResponse.setSuccess(true);

        when(userService.getProductsOfUser(userId)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<List<ProductResponseDto>>> expectedResult=ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<List<ProductResponseDto>>> actualResult=underTest.getProductsOfUser(userId);

        assertEquals(expectedResult,actualResult);


    }

    @Test
    public void getProductsOfUser_isFailure(){

        Long userId=1L;
        ApiResponse<List<ProductResponseDto>> expectedResponse=new ApiResponse<List<ProductResponseDto>>();
        expectedResponse.setSuccess(false);

        when(userService.getProductsOfUser(userId)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<List<ProductResponseDto>>> expectedResult=ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
        ResponseEntity<ApiResponse<List<ProductResponseDto>>> actualResult=underTest.getProductsOfUser(userId);

        assertEquals(expectedResult,actualResult);

    }

}

