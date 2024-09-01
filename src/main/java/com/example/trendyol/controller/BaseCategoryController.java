package com.example.trendyol.controller;


import com.example.trendyol.data.ApiPaths;
import com.example.trendyol.dto.request.BaseCategoryRequestDto;
import com.example.trendyol.dto.response.ApiResponse;
import com.example.trendyol.dto.response.BaseCategoryResponseDto;
import com.example.trendyol.model.update.BaseCategoryUpdateRequest;
import com.example.trendyol.service.BaseCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.BASE_CATEGORY)
public class BaseCategoryController {

    private final BaseCategoryService baseCategoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<BaseCategoryResponseDto>> addBaseCategory(@RequestBody BaseCategoryRequestDto baseCategoryRequestDto) {

        ApiResponse<BaseCategoryResponseDto> response = baseCategoryService.addBaseCategory(baseCategoryRequestDto);
        return response.isSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @DeleteMapping("/{base-category-id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("base-category-id") Long baseCategoryId) {
        baseCategoryService.deleteBaseCategory(baseCategoryId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BaseCategoryResponseDto>>> getAllBaseCategories() {

        ApiResponse<List<BaseCategoryResponseDto>> response = baseCategoryService.getAllCategories();
        return response.isSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PatchMapping("/{base-category-id}")
    public ResponseEntity<ApiResponse<BaseCategoryResponseDto>> updateBaseCategory(@PathVariable("base-category-id") Long baseCategoryId, @RequestBody BaseCategoryUpdateRequest baseCategoryUpdateRequest) {
        ApiResponse<BaseCategoryResponseDto> response = baseCategoryService.updateBaseCategory(baseCategoryId, baseCategoryUpdateRequest);

        return response.isSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PatchMapping("/{base-category-id}/{sub-category-id}")
    public ResponseEntity<ApiResponse<BaseCategoryResponseDto>> assignSubCategoryToBaseCategory(@PathVariable("base-category-id") Long baseCategoryId, @PathVariable("sub-category-id") Long subCategoryId) {
        System.out.println(baseCategoryId);
        ApiResponse<BaseCategoryResponseDto> response = baseCategoryService.assignSubCategory(baseCategoryId, subCategoryId);

        return response.isSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @DeleteMapping("/{baseCategoryId}/sub-categories/{subCategoryId}")
    public ResponseEntity<ApiResponse<BaseCategoryResponseDto>> removeSubCategoryFromBaseCategory(
            @PathVariable Long baseCategoryId, @PathVariable Long subCategoryId) {
        ApiResponse<BaseCategoryResponseDto> response = baseCategoryService.removeSubCategory(baseCategoryId, subCategoryId);
        return response.isSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


}
