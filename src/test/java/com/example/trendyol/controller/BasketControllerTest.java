package com.example.trendyol.controller;


import com.example.trendyol.configuration.security.JwtTokenProvider;
import com.example.trendyol.dto.response.ApiResponse;
import com.example.trendyol.dto.response.BasketResponseDto;
import com.example.trendyol.service.BasketService;
import jakarta.servlet.http.HttpServletRequest;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BasketControllerTest {

    @Mock
    BasketService basketService;

    @InjectMocks
    BasketController underTest;

    @Mock
    private HttpServletRequest request;


    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Test
    public void assignProductToUser_isSuccess() {
        // Arrange
        Long productId = 1L;
        Long userId = 1L;
        String token = "Bearer some-token";

        ApiResponse<BasketResponseDto> expectedResponse = new ApiResponse<>();
        expectedResponse.setSuccess(true);

        // Mocking behavior
        when(request.getHeader("Authorization")).thenReturn(token);
        when(jwtTokenProvider.getUserIdFromJwt(token.substring(7))).thenReturn(userId);
        when(basketService.assignProductToUser(request, productId)).thenReturn(expectedResponse);


        ResponseEntity<ApiResponse<BasketResponseDto>> expectedResult = ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<BasketResponseDto>> actualResult = underTest.assignProductToBasket(request, productId);


        assertEquals(expectedResult.getStatusCode(), actualResult.getStatusCode());
        assertEquals(expectedResult.getBody(), actualResult.getBody());
    }


    @Test
    public void assignProductToUser_isFailure() {

        Long productId = 1L;
        Long userId = 1L;
        String token = "Bearer some-token";

        ApiResponse<BasketResponseDto> expectedResponse = new ApiResponse<>();
        expectedResponse.setSuccess(false);

        when(request.getHeader("Authorization")).thenReturn(token);
        when(jwtTokenProvider.getUserIdFromJwt(token.substring(7))).thenReturn(userId);
        when(basketService.assignProductToUser(request, productId)).thenReturn(expectedResponse);


        ResponseEntity<ApiResponse<BasketResponseDto>> expectedResult = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
        ResponseEntity<ApiResponse<BasketResponseDto>> actualResult = underTest.assignProductToBasket(request, productId);


        assertEquals(expectedResult, actualResult);
    }


    @Test
    public void removeProductFromUser_isSuccess() {
        // Arrange
        Long productId = 1L;
        Long userId = 1L;
        String token = "Bearer some-token";

        ApiResponse<BasketResponseDto> expectedResponse = new ApiResponse<>();
        expectedResponse.setSuccess(true);

        // Mocking behavior
        when(request.getHeader("Authorization")).thenReturn(token);
        when(jwtTokenProvider.getUserIdFromJwt(token.substring(7))).thenReturn(userId);
        when(basketService.removeProductFromUser(request, productId)).thenReturn(expectedResponse);


        ResponseEntity<ApiResponse<BasketResponseDto>> expectedResult = ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<BasketResponseDto>> actualResult = underTest.removeProductfromBasket(request, productId);


        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void removeProductFromUser_isFailure() {

        Long productId = 1L;
        Long userId = 1L;
        String token = "Bearer some-token";

        ApiResponse<BasketResponseDto> expectedResponse = new ApiResponse<>();
        expectedResponse.setSuccess(false); // Failure scenario

        when(request.getHeader("Authorization")).thenReturn(token);
        when(jwtTokenProvider.getUserIdFromJwt(token.substring(7))).thenReturn(userId);
        when(basketService.removeProductFromUser(request, productId)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<BasketResponseDto>> expectedResult = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
        ResponseEntity<ApiResponse<BasketResponseDto>> actualResult = underTest.removeProductfromBasket(request, productId);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getAllBaskets_isSuccess() {

        ApiResponse<List<BasketResponseDto>> expectedResponse = new ApiResponse<List<BasketResponseDto>>();
        expectedResponse.setSuccess(true);

        when(basketService.getAllBaskets()).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<List<BasketResponseDto>>> expectedResult = ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<List<BasketResponseDto>>> actualResult = underTest.getAllBaskets();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getAllBaskets_isFailure() {

        ApiResponse<List<BasketResponseDto>> expectedResponse = new ApiResponse<List<BasketResponseDto>>();
        expectedResponse.setSuccess(false);

        when(basketService.getAllBaskets()).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<List<BasketResponseDto>>> expectedResult = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
        ResponseEntity<ApiResponse<List<BasketResponseDto>>> actualResult = underTest.getAllBaskets();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getBasket_isSuccess(){

        Long userId=1L;
        ApiResponse<BasketResponseDto> expectedResponse=new ApiResponse<BasketResponseDto>();
        expectedResponse.setSuccess(true);

        when(basketService.getBasket(userId)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<BasketResponseDto>> expectedResult=ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<BasketResponseDto>> actualResult=underTest.getBasket(userId);

        assertEquals(expectedResult,actualResult);

    }

    @Test
    public void getBasket_isFailure(){

        Long userId=1L;
        ApiResponse<BasketResponseDto> expectedResponse=new ApiResponse<BasketResponseDto>();
        expectedResponse.setSuccess(false);

        when(basketService.getBasket(userId)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<BasketResponseDto>> expectedResult=ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
        ResponseEntity<ApiResponse<BasketResponseDto>> actualResult=underTest.getBasket(userId);

        assertEquals(expectedResult,actualResult);

    }



}

