package com.example.trendyol.controller;


import com.example.trendyol.data.ApiPaths;
import com.example.trendyol.dto.request.SubCategoryRequestDto;
import com.example.trendyol.dto.response.ApiResponse;
import com.example.trendyol.dto.response.ProductResponseDto;
import com.example.trendyol.dto.response.SubCategoryResponseDto;
import com.example.trendyol.model.postgres.ProductModel;
import com.example.trendyol.model.update.SubCategoryUpdateRequest;
import com.example.trendyol.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.SUB_CATEGORY)
@RequiredArgsConstructor
public class SubCategoryController {

    private final SubCategoryService subCategoryService;


    @PostMapping
    public ResponseEntity<ApiResponse<SubCategoryResponseDto>> addSubCategory(@RequestBody SubCategoryRequestDto subCategoryRequestDto) {

        ApiResponse<SubCategoryResponseDto> response = subCategoryService.addSubCategory(subCategoryRequestDto);
        return response.isSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }

    @DeleteMapping("/{sub-category-id}")
    public ResponseEntity<ApiResponse<Void>> deleteSubCategory(@PathVariable("sub-category-id") Long subCategoryId) {
        ApiResponse<Void> response = subCategoryService.deleteSubCategory(subCategoryId);
        return response.isSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<SubCategoryResponseDto>>> getAllSubCategories() {
        ApiResponse<List<SubCategoryResponseDto>> response = subCategoryService.getAllDtos();
        return response.isSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PatchMapping("/{sub-category-id}")
    public ResponseEntity<ApiResponse<SubCategoryResponseDto>> updateSubCategory(@PathVariable("sub-category-id") Long subCategoryId, @RequestBody SubCategoryUpdateRequest subCategoryUpdateRequest) {

    ApiResponse<SubCategoryResponseDto> response=subCategoryService.updateSubCategory(subCategoryId, subCategoryUpdateRequest);
        return response.isSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    @GetMapping("/get-products-of-subcategory/{sub-category-id}")
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> getProductOfSubCategory(@PathVariable("sub-category-id") Long subCategoryId){
       ApiResponse<List<ProductResponseDto>> response=subCategoryService.getProductsById(subCategoryId);

        return response.isSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }

}
