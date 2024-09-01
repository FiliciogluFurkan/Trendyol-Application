package com.example.trendyol.controller;


import com.example.trendyol.data.ApiPaths;
import com.example.trendyol.dto.response.ApiResponse;
import com.example.trendyol.dto.response.ProductResponseDto;
import com.example.trendyol.dto.response.UserResponseDto;
import com.example.trendyol.model.roles.RoleModel;
import com.example.trendyol.model.update.UserUpdateRequest;
import com.example.trendyol.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @DeleteMapping("/delete/{user-id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable("user-id") Long userId) {
        ApiResponse<Void> response = userService.deleteUser(userId);
        return response.isSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping("/get-by-id/{user-id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> getUser(@PathVariable("user-id") Long userId) {
        ApiResponse<UserResponseDto> response=userService.getUser(userId);
        return response.isSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> getUsers() {
        ApiResponse<List<UserResponseDto>> response=userService.getUsers();
        return response.isSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PatchMapping("/update/{user-id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> updateUser(@PathVariable("user-id") Long userId, @RequestBody UserUpdateRequest userUpdateRequest) {

        ApiResponse<UserResponseDto> response=userService.updateUser(userId, userUpdateRequest);

        return response.isSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping("get-products-of-user/{user-id}")
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> getProductsOfUser(@PathVariable("user-id") Long userId){

        ApiResponse<List<ProductResponseDto>> response=userService.getProductsOfUser(userId);

        return response.isSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);


    }
}
