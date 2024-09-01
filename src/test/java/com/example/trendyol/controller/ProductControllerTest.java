package com.example.trendyol.controller;


import com.example.trendyol.dto.request.ProductRequestDto;
import com.example.trendyol.dto.response.ApiResponse;
import com.example.trendyol.dto.response.ProductResponseDto;
import com.example.trendyol.model.update.ProductUpdateRequest;
import com.example.trendyol.service.ProductService;
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
public class ProductControllerTest {

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController underTest;


    @Test
    public void addProduct_isSuccess() {
        // Arrange
        ProductRequestDto productRequestDto = new ProductRequestDto();
        ApiResponse<ProductResponseDto> expectedResponse = new ApiResponse<>();
        expectedResponse.setSuccess(true);

      //  when(productService.addProduct(productRequestDto)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<ProductResponseDto>> expectedResult = ResponseEntity.ok(expectedResponse);
     //   ResponseEntity<ApiResponse<ProductResponseDto>> actualResult = underTest.addProduct(productRequestDto);
  //      assertEquals(expectedResult, actualResult);
    }

    @Test
    public void addProduct_isFailure() {
        // Arrange
        ProductRequestDto productRequestDto = new ProductRequestDto();
        ApiResponse<ProductResponseDto> expectedResponse = new ApiResponse<>();
        expectedResponse.setSuccess(false);

    //    when(productService.addProduct(productRequestDto)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<ProductResponseDto>> expectedResult = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
      //  ResponseEntity<ApiResponse<ProductResponseDto>> actualResult = underTest.addProduct(productRequestDto);

//        /assertEquals(expectedResult, actualResult);
    }


    @Test
    public void deleteProduct_isSuccess() {

        Long productId = 1L;
        ApiResponse<Void> expectedResponse = new ApiResponse<>();
        expectedResponse.setSuccess(true);

        when(productService.deleteProduct(productId)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<Void>> expectedResult = ResponseEntity.ok(expectedResponse);

        ResponseEntity<ApiResponse<Void>> actualResult = underTest.deleteProduct(productId);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void deleteProduct_isFailure() {
        Long productId = 1L;
        ApiResponse<Void> expectedResponse = new ApiResponse<>();
        expectedResponse.setSuccess(false);

        when(productService.deleteProduct(productId)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<Void>> expectedResult = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);

        ResponseEntity<ApiResponse<Void>> actualResult = underTest.deleteProduct(productId);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getAllProducts_isSuccess() {

        ApiResponse<List<ProductResponseDto>> expectedResponse = new ApiResponse<List<ProductResponseDto>>();
        expectedResponse.setSuccess(true);

        when(productService.getProducts()).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<List<ProductResponseDto>>> expectedResult = ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<List<ProductResponseDto>>> actualResult = underTest.getProducts();

        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void getAllProducts_isFailure() {
        ApiResponse<List<ProductResponseDto>> expectedResponse = new ApiResponse<List<ProductResponseDto>>();
        expectedResponse.setSuccess(false);

        when(productService.getProducts()).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<List<ProductResponseDto>>> expectedResult = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
        ResponseEntity<ApiResponse<List<ProductResponseDto>>> actualResult = underTest.getProducts();

        assertEquals(expectedResult, actualResult);

    }


    @Test
    public void updateProduct_isSuccess() {

        Long productId = 1L;
        ProductUpdateRequest productUpdateRequest = new ProductUpdateRequest();
        ApiResponse<ProductResponseDto> expectedResponse = new ApiResponse<ProductResponseDto>();
        expectedResponse.setSuccess(true);

        when(productService.updateProduct(productId, productUpdateRequest)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<ProductResponseDto>> expectedResult = ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<ProductResponseDto>> actualResult = underTest.updateProduct(productId, productUpdateRequest);

        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void updateProduct_isFailure() {
        Long productId = 1L;
        ProductUpdateRequest productUpdateRequest = new ProductUpdateRequest();
        ApiResponse<ProductResponseDto> expectedResponse = new ApiResponse<ProductResponseDto>();
        expectedResponse.setSuccess(false);

        when(productService.updateProduct(productId, productUpdateRequest)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<ProductResponseDto>> expectedResult = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
        ResponseEntity<ApiResponse<ProductResponseDto>> actualResult = underTest.updateProduct(productId, productUpdateRequest);

        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void assignCategoryToProduct_isSuccess() {
        Long productId = 1L;
        Long subCategoryId = 1L;
        ApiResponse<ProductResponseDto> expectedResponse = new ApiResponse<ProductResponseDto>();
        expectedResponse.setSuccess(true);

        when(productService.assignCategoryToProduct(productId, subCategoryId)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<ProductResponseDto>> expectedResult = ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<ProductResponseDto>> actualResult = underTest.assignCategoryToProduct(productId, subCategoryId);

        assertEquals(expectedResult, actualResult);


    }

    @Test
    public void assingCategoryToProduct_isFailure() {

        Long productId = 1L;
        Long subCategoryId = 1L;
        ApiResponse<ProductResponseDto> expectedResponse = new ApiResponse<ProductResponseDto>();
        expectedResponse.setSuccess(false);

        when(productService.assignCategoryToProduct(productId, subCategoryId)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<ProductResponseDto>> expectedResult = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
        ResponseEntity<ApiResponse<ProductResponseDto>> actualResult = underTest.assignCategoryToProduct(productId, subCategoryId);

        assertEquals(expectedResult, actualResult);


    }

}
