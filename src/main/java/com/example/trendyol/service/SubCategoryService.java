package com.example.trendyol.service;


import com.example.trendyol.dto.request.SubCategoryRequestDto;
import com.example.trendyol.dto.response.ApiResponse;
import com.example.trendyol.dto.response.ProductResponseDto;
import com.example.trendyol.dto.response.SubCategoryResponseDto;
import com.example.trendyol.mapper.ProductMapper;
import com.example.trendyol.mapper.SubCategoryMapper;
import com.example.trendyol.model.mongo.SubCategoryModel;
import com.example.trendyol.model.postgres.ProductModel;
import com.example.trendyol.model.update.SubCategoryUpdateRequest;
import com.example.trendyol.repository.SubCategoryRepository;

import static com.example.trendyol.validation.ValidationMessages.*;

import com.example.trendyol.validation.ValidationMessages;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SubCategoryService {

    private static final Logger log = LoggerFactory.getLogger(SubCategoryService.class);
    private final SubCategoryRepository subCategoryRepository;
    private final SubCategoryMapper subCategoryMapper;
    private final ProductMapper productMapper;

    public ApiResponse<SubCategoryResponseDto> addSubCategory(SubCategoryRequestDto subCategoryRequestDto) {
        if (subCategoryRepository.existsByName(subCategoryRequestDto.getName())) {
            ApiResponse.failure(SUB_CATEGORY_ALREADY_ADDED);
        }

        return ApiResponse.success(SUB_CATEGORY_ADDED_SUCCESFULLY, subCategoryMapper.toResponse(subCategoryRepository.save(subCategoryMapper.toModel(subCategoryRequestDto))));

    }

    public ApiResponse<Void> deleteSubCategory(Long subCategoryId) {

        SubCategoryModel subCategoryModel = subCategoryRepository.findById(subCategoryId).orElse(null);

        if (subCategoryModel == null || !subCategoryModel.isVisible()) {
            log.info("Failed to delete sub category with id {}", subCategoryId);
            return ApiResponse.failure(SUB_CATEGORY_NOT_FOUND);
        }


        log.info("Sub category deleted succesfully");
        subCategoryModel.setVisible(false);
        subCategoryRepository.save(subCategoryModel);
        return ApiResponse.success(SUB_CATEGORY_DELETED_SUCCESFULLY);
    }


    public ApiResponse<List<SubCategoryResponseDto>> getAllDtos() {

        List<SubCategoryResponseDto> listOfDtos = subCategoryRepository.findAll().stream().filter(subCategoryModel -> Objects.equals(Boolean.TRUE,subCategoryModel.isVisible()))
                .map(subCategoryMapper::toResponse).toList();

        for (SubCategoryModel model : subCategoryRepository.findAll()) {
            if (model.isVisible()) {
                listOfDtos.add(subCategoryMapper.toResponse(model));
            }
        }

        return ApiResponse.success(SUB_CATEGORIES_RETRIEVED_SUCCESFULLY, listOfDtos);
    }


    public ApiResponse<SubCategoryResponseDto> updateSubCategory(Long subCategoryId, SubCategoryUpdateRequest subCategoryUpdateRequest) {

        SubCategoryModel subCategoryModel = subCategoryRepository.findById(subCategoryId).orElse(null);

        if (subCategoryModel == null || !subCategoryModel.isVisible()) {
            log.error("Failed to update subcategory.Sub category not found with id {}", subCategoryId);
            ApiResponse.failure(SUB_CATEGORY_NOT_FOUND);
        }


        if (subCategoryUpdateRequest.getName() != null) {
            subCategoryModel.setName(subCategoryUpdateRequest.getName());
        }

        subCategoryModel.setUpdatedDateTime(ZonedDateTime.now());

        return ApiResponse.success(SUB_CATEGORY_UPDATE, subCategoryMapper.toResponse(subCategoryModel));


    }

    public ApiResponse<List<ProductResponseDto>> getProductsById(Long subCategoryId) {


        SubCategoryModel subCategoryModel = subCategoryRepository.findById(subCategoryId).orElse(null);
        if (subCategoryModel == null) {
            log.error("Sub category not found with id {}", subCategoryId);
            return ApiResponse.failure(SUB_CATEGORY_NOT_FOUND);
        }
        List<ProductResponseDto> listOfProductResponseDtos = subCategoryModel.getProducts().stream()
                .filter(productModel -> Objects.equals(Boolean.TRUE,productModel.isVisible())).map(productMapper::toResponse).toList();

        return ApiResponse.success(PRODUCTS_RETRIEVED_SUCCESFULLY, listOfProductResponseDtos);

    }
}
