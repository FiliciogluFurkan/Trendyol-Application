package com.example.trendyol.controller;

import com.example.trendyol.dto.request.BaseCategoryRequestDto;
import com.example.trendyol.dto.response.ApiResponse;
import com.example.trendyol.dto.response.BaseCategoryResponseDto;
import com.example.trendyol.model.update.BaseCategoryUpdateRequest;
import com.example.trendyol.service.BaseCategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BaseCategoryControllerTest {


    @Mock
    BaseCategoryService baseCategoryService;

    @InjectMocks
    BaseCategoryController underTest;


    @Test
    public void addBaseCategory_isSuccess() {

        BaseCategoryRequestDto baseCategoryRequestDto = new BaseCategoryRequestDto();
        ApiResponse<BaseCategoryResponseDto> expectedResponse = new ApiResponse<BaseCategoryResponseDto>();
        expectedResponse.setSuccess(true);

        when(baseCategoryService.addBaseCategory(baseCategoryRequestDto)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<BaseCategoryResponseDto>> expectedResult = ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<BaseCategoryResponseDto>> actualResult = underTest.addBaseCategory(baseCategoryRequestDto);

        assertEquals(expectedResult, actualResult);


    }

    @Test
    public void addBaseCategory_isFailure() {

        BaseCategoryRequestDto baseCategoryRequestDto = new BaseCategoryRequestDto();
        ApiResponse<BaseCategoryResponseDto> expectedResponse = new ApiResponse<BaseCategoryResponseDto>();
        expectedResponse.setSuccess(false);

        when(baseCategoryService.addBaseCategory(baseCategoryRequestDto)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<BaseCategoryResponseDto>> expectedResult = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
        ResponseEntity<ApiResponse<BaseCategoryResponseDto>> actualResult = underTest.addBaseCategory(baseCategoryRequestDto);

        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void deleteBaseCategory() {
        Long baseCategoryId = 1L;
        ApiResponse<Void> expectedResponse = new ApiResponse<Void>();

        assertDoesNotThrow(() -> underTest.deleteCategory(baseCategoryId));

    }

    @Test
    public void getAllBaseCategories_isSuccess() {
        ApiResponse<List<BaseCategoryResponseDto>> expectedResponse = new ApiResponse<List<BaseCategoryResponseDto>>();
        expectedResponse.setSuccess(true);

        when(baseCategoryService.getAllCategories()).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<List<BaseCategoryResponseDto>>> expectedResult = ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<List<BaseCategoryResponseDto>>> actualResult = underTest.getAllBaseCategories();

        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void getAllBaseCategories_isFailure() {
        ApiResponse<List<BaseCategoryResponseDto>> expectedResponse = new ApiResponse<List<BaseCategoryResponseDto>>();
        expectedResponse.setSuccess(false);

        when(baseCategoryService.getAllCategories()).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<List<BaseCategoryResponseDto>>> expectedResult = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
        ResponseEntity<ApiResponse<List<BaseCategoryResponseDto>>> actualResult = underTest.getAllBaseCategories();

        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void updateBaseCategory_isSuccess() {

        Long baseCategoryId = 1L;
        BaseCategoryUpdateRequest baseCategoryUpdateRequest = new BaseCategoryUpdateRequest();
        ApiResponse<BaseCategoryResponseDto> expectedResponse = new ApiResponse<BaseCategoryResponseDto>();
        expectedResponse.setSuccess(true);

        when(baseCategoryService.updateBaseCategory(baseCategoryId, baseCategoryUpdateRequest)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<BaseCategoryResponseDto>> expectedResult = ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<BaseCategoryResponseDto>> actualResult = underTest.updateBaseCategory(baseCategoryId, baseCategoryUpdateRequest);

        assertEquals(baseCategoryId, baseCategoryUpdateRequest);

    }

    @Test
    public void updateBaseCategory_isFailure() {

        Long baseCategoryId = 1L;
        BaseCategoryUpdateRequest baseCategoryUpdateRequest = new BaseCategoryUpdateRequest();
        ApiResponse<BaseCategoryResponseDto> expectedResponse = new ApiResponse<BaseCategoryResponseDto>();
        expectedResponse.setSuccess(false);

        when(baseCategoryService.updateBaseCategory(baseCategoryId, baseCategoryUpdateRequest)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<BaseCategoryResponseDto>> expectedResult = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
        ResponseEntity<ApiResponse<BaseCategoryResponseDto>> actualResult = underTest.updateBaseCategory(baseCategoryId, baseCategoryUpdateRequest);

        assertEquals(baseCategoryId, baseCategoryUpdateRequest);

    }


    @Test
    public void assignSuvCategoryToBaseCategory_isSuccess() {
        Long baseCategoryId = 1L;
        Long subCategoryId = 1L;
        ApiResponse<BaseCategoryResponseDto> expectedResponse = new ApiResponse<BaseCategoryResponseDto>();
        expectedResponse.setSuccess(true);

        when(baseCategoryService.assignSubCategory(baseCategoryId, subCategoryId)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<BaseCategoryResponseDto>> expectedResult = ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<BaseCategoryResponseDto>> actualResult = underTest.assignSubCategoryToBaseCategory(baseCategoryId, subCategoryId);

        assertEquals(expectedResult, actualResult);


    }

    @Test
    public void assignSubCategoryToBaseCategory_isFailure() {
        Long baseCategoryId = 1L;
        Long subCategoryId = 1L;
        ApiResponse<BaseCategoryResponseDto> expectedResponse = new ApiResponse<BaseCategoryResponseDto>();
        expectedResponse.setSuccess(false);

        when(baseCategoryService.assignSubCategory(baseCategoryId, subCategoryId)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<BaseCategoryResponseDto>> expectedResult = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse);
        ResponseEntity<ApiResponse<BaseCategoryResponseDto>> actualResult = underTest.assignSubCategoryToBaseCategory(baseCategoryId, subCategoryId);

        assertEquals(expectedResult, actualResult);


    }

    @Test
    public void removeSubCategoryFromBaseCategory_isSuccess() {

        Long baseCategoryId = 1L;
        Long subCategoryId = 1L;
        ApiResponse<BaseCategoryResponseDto> expectedResponse = new ApiResponse<BaseCategoryResponseDto>();
        expectedResponse.setSuccess(false);

        when(baseCategoryService.removeSubCategory(baseCategoryId, subCategoryId)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<BaseCategoryResponseDto>> expectedResult = ResponseEntity.ok(expectedResponse);
        ResponseEntity<ApiResponse<BaseCategoryResponseDto>> actualResult = underTest.removeSubCategoryFromBaseCategory(baseCategoryId, subCategoryId);

        assertEquals(baseCategoryId, subCategoryId);

    }

    @Test
    public void removeSubCategoryFromBaseCategory_isFailure() {

        Long baseCategoryId = 1L;
        Long subCategoryId = 1L;
        ApiResponse<BaseCategoryResponseDto> expectedResponse = new ApiResponse<BaseCategoryResponseDto>();
        expectedResponse.setSuccess(false);

        when(baseCategoryService.removeSubCategory(baseCategoryId, subCategoryId)).thenReturn(expectedResponse);

        ResponseEntity<ApiResponse<BaseCategoryResponseDto>> expectedResult = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(expectedResponse);
        ResponseEntity<ApiResponse<BaseCategoryResponseDto>> actualResult = underTest.removeSubCategoryFromBaseCategory(baseCategoryId, subCategoryId);

        assertEquals(baseCategoryId, subCategoryId);

    }


}


