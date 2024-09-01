package com.example.trendyol.controller;

import com.example.trendyol.dto.request.RefreshRequest;
import com.example.trendyol.dto.request.UserRequest;
import com.example.trendyol.dto.request.UserRequestDto;
import com.example.trendyol.dto.response.ApiResponse;
import com.example.trendyol.dto.response.AuthResponse;
import com.example.trendyol.dto.response.UserResponseDto;
import com.example.trendyol.service.AuthService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    AuthService authService;

    @InjectMocks
    AuthController underTest;

    @Test
    public void login() {

        UserRequest userRequest = new UserRequest();
        AuthResponse expectedResponse = new AuthResponse();

        when(authService.login(userRequest)).thenReturn(expectedResponse);

        AuthResponse actualResponse = underTest.login(userRequest);

        assertEquals(expectedResponse, actualResponse);

    }

    @Test
    public void register_isSuccess() {

        UserRequestDto userRequestDto = new UserRequestDto();
        ApiResponse<UserResponseDto> expectedResponse = new ApiResponse<UserResponseDto>();
        expectedResponse.setSuccess(true);

        when(authService.register(userRequestDto)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<UserResponseDto>> expectedResult=ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<UserResponseDto>> actualResult=underTest.register(userRequestDto);

        assertEquals(expectedResult,actualResult);

    }

    @Test
    public void register_isFailure() {

        UserRequestDto userRequestDto = new UserRequestDto();
        ApiResponse<UserResponseDto> expectedResponse = new ApiResponse<UserResponseDto>();
        expectedResponse.setSuccess(false);

        when(authService.register(userRequestDto)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<UserResponseDto>> expectedResult=ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
        ResponseEntity<ApiResponse<UserResponseDto>> actualResult=underTest.register(userRequestDto);

        assertEquals(expectedResult,actualResult);

    }

    @Test
    public void refresh(){
        RefreshRequest refreshRequest=new RefreshRequest();
        AuthResponse authResponse=new AuthResponse();
        ResponseEntity<AuthResponse> expectedResult=ResponseEntity.ok(authResponse);

        when(authService.createRefresh(refreshRequest)).thenReturn(expectedResult);
        ResponseEntity<AuthResponse> actualResult=underTest.refresh(refreshRequest);

        assertEquals(expectedResult,actualResult);
        
    }

}

