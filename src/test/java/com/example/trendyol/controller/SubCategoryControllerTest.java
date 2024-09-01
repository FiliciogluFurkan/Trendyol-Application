package com.example.trendyol.controller;


import com.example.trendyol.dto.request.SubCategoryRequestDto;
import com.example.trendyol.dto.response.ApiResponse;
import com.example.trendyol.dto.response.ProductResponseDto;
import com.example.trendyol.dto.response.SubCategoryResponseDto;
import com.example.trendyol.model.update.SubCategoryUpdateRequest;
import com.example.trendyol.service.SubCategoryService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.http.HttpResponse;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SubCategoryControllerTest {

    @Mock
    SubCategoryService subCategoryService;
    @InjectMocks
    SubCategoryController underTest;

    @Test
    public void addSubCategory_isSuccess() {
        SubCategoryRequestDto subCategoryRequestDto = new SubCategoryRequestDto();
        ApiResponse<SubCategoryResponseDto> expectedResponse = new ApiResponse<SubCategoryResponseDto>();
        expectedResponse.setSuccess(true);

        when(subCategoryService.addSubCategory(subCategoryRequestDto)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<SubCategoryResponseDto>> expectedResult = ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<SubCategoryResponseDto>> actualResult = underTest.addSubCategory(subCategoryRequestDto);

        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void addSubCategory_isFailure() {
        SubCategoryRequestDto subCategoryRequestDto = new SubCategoryRequestDto();
        ApiResponse<SubCategoryResponseDto> expectedResponse = new ApiResponse<SubCategoryResponseDto>();
        expectedResponse.setSuccess(false);

        when(subCategoryService.addSubCategory(subCategoryRequestDto)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<SubCategoryResponseDto>> expectedResult = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
        ResponseEntity<ApiResponse<SubCategoryResponseDto>> actualResult = underTest.addSubCategory(subCategoryRequestDto);

        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void deleteSubCategory_isSuccess() {
        Long subCategoryId = 1L;
        ApiResponse<Void> expectedResponse = new ApiResponse<Void>();
        expectedResponse.setSuccess(true);

        when(subCategoryService.deleteSubCategory(subCategoryId)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<Void>> expectedResult = ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<Void>> actualResult = underTest.deleteSubCategory(subCategoryId);

        assertEquals(expectedResult, actualResult);


    }

    @Test
    public void deleteSubCategory_isFailure() {
        Long subCategoryId = 1L;
        ApiResponse<Void> expectedResponse = new ApiResponse<Void>();
        expectedResponse.setSuccess(false);

        when(subCategoryService.deleteSubCategory(subCategoryId)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<Void>> expectedResult = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
        ResponseEntity<ApiResponse<Void>> actualResult = underTest.deleteSubCategory(subCategoryId);

        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void getAllSubCategories_isSuccess() {

        ApiResponse<List<SubCategoryResponseDto>> expectedResponse = new ApiResponse<List<SubCategoryResponseDto>>();
        expectedResponse.setSuccess(true);

        when(subCategoryService.getAllDtos()).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<List<SubCategoryResponseDto>>> expectedResult = ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<List<SubCategoryResponseDto>>> actualResult = underTest.getAllSubCategories();

        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void getAllSubCategories_isFailure() {

        ApiResponse<List<SubCategoryResponseDto>> expectedResponse = new ApiResponse<List<SubCategoryResponseDto>>();
        expectedResponse.setSuccess(false);

        when(subCategoryService.getAllDtos()).thenReturn(expectedResponse);


        ResponseEntity<ApiResponse<List<SubCategoryResponseDto>>> expectedResult = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
        ResponseEntity<ApiResponse<List<SubCategoryResponseDto>>> actualResult = underTest.getAllSubCategories();

        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void updateSubCategory_isSuccess() {
        Long subCategoryId = 1L;
        SubCategoryUpdateRequest subCategoryUpdateRequest = new SubCategoryUpdateRequest();
        ApiResponse<SubCategoryResponseDto> expectedResponse = new ApiResponse<SubCategoryResponseDto>();
        expectedResponse.setSuccess(true);

        when(subCategoryService.updateSubCategory(subCategoryId, subCategoryUpdateRequest)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<SubCategoryResponseDto>> expectedResult = ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<SubCategoryResponseDto>> actualResult = underTest.updateSubCategory(subCategoryId, subCategoryUpdateRequest);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void updateSubCategory_isFailure() {
        Long subCategoryId = 1L;
        SubCategoryUpdateRequest subCategoryUpdateRequest = new SubCategoryUpdateRequest();
        ApiResponse<SubCategoryResponseDto> expectedResponse = new ApiResponse<SubCategoryResponseDto>();
        expectedResponse.setSuccess(false);

        when(subCategoryService.updateSubCategory(subCategoryId, subCategoryUpdateRequest)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<SubCategoryResponseDto>> expectedResult = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
        ResponseEntity<ApiResponse<SubCategoryResponseDto>> actualResult = underTest.updateSubCategory(subCategoryId, subCategoryUpdateRequest);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getProductsOfSubCategory_isSuccess() {

        Long subCategoryId = 1L;
        ApiResponse<List<ProductResponseDto>> expectedResponse = new ApiResponse<List<ProductResponseDto>>();
        expectedResponse.setSuccess(true);

        when(subCategoryService.getProductsById(subCategoryId)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<List<ProductResponseDto>>> expectedResult = ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<List<ProductResponseDto>>> actualResult=underTest.getProductOfSubCategory(subCategoryId);

        assertEquals(expectedResult,actualResult);

    }


    @Test
    public void getProductsOfSubCategory_isFailure() {

        Long subCategoryId = 1L;
        ApiResponse<List<ProductResponseDto>> expectedResponse = new ApiResponse<List<ProductResponseDto>>();
        expectedResponse.setSuccess(false);

        when(subCategoryService.getProductsById(subCategoryId)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<List<ProductResponseDto>>> expectedResult = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
        ResponseEntity<ApiResponse<List<ProductResponseDto>>> actualResult=underTest.getProductOfSubCategory(subCategoryId);

        assertEquals(expectedResult,actualResult);
    }

}

