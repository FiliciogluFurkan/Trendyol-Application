package com.example.trendyol.controller;


import com.example.trendyol.data.ApiPaths;
import com.example.trendyol.dto.request.RefreshRequest;
import com.example.trendyol.dto.request.UserRequest;
import com.example.trendyol.dto.request.UserRequestDto;
import com.example.trendyol.dto.response.ApiResponse;
import com.example.trendyol.dto.response.AuthResponse;
import com.example.trendyol.dto.response.UserResponseDto;
import com.example.trendyol.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.AUTH)
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody UserRequest loginRequest) {

        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDto>> register(@RequestBody UserRequestDto userRequestDto) {

        ApiResponse<UserResponseDto> dtoApiResponse = authService.register(userRequestDto);
        return dtoApiResponse.isSuccess() ? ResponseEntity.ok(dtoApiResponse) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dtoApiResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest refreshRequest) {

        ResponseEntity<AuthResponse> response = authService.createRefresh(refreshRequest);
        return response;
    }

}
