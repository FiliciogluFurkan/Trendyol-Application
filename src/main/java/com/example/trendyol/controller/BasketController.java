package com.example.trendyol.controller;

import com.example.trendyol.configuration.security.JwtTokenProvider;
import com.example.trendyol.data.ApiPaths;
import com.example.trendyol.dto.response.ApiResponse;
import com.example.trendyol.dto.response.BasketResponseDto;
import com.example.trendyol.service.BasketService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.BASKET)
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    @PostMapping("/product-to-basket/{product-id}")
    public ResponseEntity<ApiResponse<BasketResponseDto>> assignProductToBasket(HttpServletRequest request, @PathVariable("product-id") Long productId) {
        ApiResponse<BasketResponseDto> response = basketService.assignProductToUser(request, productId);
        return response.isSuccess() ? ResponseEntity.ok(response) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PostMapping("remove-product-from-basket/{product-id}")
    public ResponseEntity<ApiResponse<BasketResponseDto>> removeProductfromBasket(HttpServletRequest request, @PathVariable("product-id") Long productId) {
        ApiResponse<BasketResponseDto> response = basketService.removeProductFromUser(request, productId);
        return response.isSuccess() ? ResponseEntity.ok(response) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<BasketResponseDto>>> getAllBaskets() {
        ApiResponse<List<BasketResponseDto>> response = basketService.getAllBaskets();
        return response.isSuccess() ? ResponseEntity.ok(response) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping("/get-by-id/{user-id}")
    public ResponseEntity<ApiResponse<BasketResponseDto>> getBasket(@PathVariable("user-id") Long userId) {
        ApiResponse<BasketResponseDto> response = basketService.getBasket(userId);
        return response.isSuccess() ? ResponseEntity.ok(response) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
