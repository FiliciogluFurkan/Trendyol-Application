package com.example.trendyol.service;

import com.example.trendyol.dto.request.BaseCategoryRequestDto;
import com.example.trendyol.dto.response.ApiResponse;
import com.example.trendyol.dto.response.BaseCategoryResponseDto;
import com.example.trendyol.mapper.BaseCategoryMapper;
import com.example.trendyol.model.mongo.BaseCategoryModel;
import com.example.trendyol.model.mongo.SubCategoryModel;
import com.example.trendyol.model.update.BaseCategoryUpdateRequest;
import com.example.trendyol.repository.BaseCategoryRepository;
import com.example.trendyol.repository.SubCategoryRepository;

import static com.example.trendyol.validation.ValidationMessages.*;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BaseCategoryService {

    private static final Logger log = LoggerFactory.getLogger(BaseCategoryService.class);
    private final BaseCategoryRepository baseCategoryRepository;
    private final BaseCategoryMapper baseCategoryMapper;
    private final SubCategoryRepository subCategoryRepository;

    public ApiResponse<BaseCategoryResponseDto> addBaseCategory(BaseCategoryRequestDto baseCategoryRequestDto) {
        if (baseCategoryRepository.existsByName(baseCategoryRequestDto.getName())) {
            log.error("category already added, {}", baseCategoryRequestDto.getName());
            return ApiResponse.failure(BASE_CATEGORY_ALREADY_ADDED);
        }

        return ApiResponse.success(BASE_CATEGORY_ADDED_SUCCESFULLY, baseCategoryMapper.toResponse(baseCategoryRepository.save(baseCategoryMapper.toModel(baseCategoryRequestDto))));
    }

    public void deleteBaseCategory(Long baseCategoryId) {

        baseCategoryRepository.findById(baseCategoryId).ifPresentOrElse(
                (baseCategoryModel) -> {
                    if (baseCategoryModel.getSubCategoryModelList() == null) {
                        log.info("Category Deleted Succesfully");
                        baseCategoryModel.setVisible(false);
                        baseCategoryRepository.save(baseCategoryModel);

                    } else {
                        ApiResponse.failure(BASE_CATEGORY_HAS_SUB_CATEGORY);
                    }
                }, () -> {
                    log.info("Failed to remove category with id {}", baseCategoryId);
                    ApiResponse.failure(BASE_CATEGORY_NOT_FOUND);
                }
        );

    }

    public ApiResponse<List<BaseCategoryResponseDto>> getAllCategories() {
        List<BaseCategoryResponseDto> listOfDto = new ArrayList<>();

        for (BaseCategoryModel model : baseCategoryRepository.findAll()) {
            if (model.isVisible()) {
                listOfDto.add(baseCategoryMapper.toResponse(model));
            }
        }

        return ApiResponse.success(BASE_CATEGORIES_RETRIEVED_SUCCESFULLY, listOfDto);
    }

    public ApiResponse<BaseCategoryResponseDto> assignSubCategory(Long baseCategoryId, Long subCategoryId) {
        // BaseCategoryModel için Optional
        BaseCategoryModel baseCategoryModel = baseCategoryRepository.findById(baseCategoryId)
                .orElse(null);

        if (baseCategoryModel == null) {
            log.error("Failed to find BaseCategory with ID: {}", baseCategoryId);
            return ApiResponse.failure("BASE_CATEGORY_NOT_FOUND");
        }

        // SubCategoryModel için Optional
        SubCategoryModel subCategoryModel = subCategoryRepository.findById(subCategoryId)
                .orElse(null);

        if (subCategoryModel == null) {
            log.error("Failed to find SubCategory with ID: {}", subCategoryId);
            return ApiResponse.failure(SUB_CATEGORY_NOT_FOUND);
        }

        BaseCategoryModel categoryModel = subCategoryModel.getBaseCategory();
        subCategoryModel.setBaseCategory(baseCategoryModel);
        subCategoryRepository.save(subCategoryModel);
        return ApiResponse.success(SUB_CATEGORY_ASSIGNED_SUCCESFULLY, baseCategoryMapper.toResponse(baseCategoryModel));

    }


    public ApiResponse<BaseCategoryResponseDto> removeSubCategory(Long baseCategoryId, Long subCategoryId) {

        BaseCategoryModel baseCategoryModel = baseCategoryRepository.findById(baseCategoryId)
                .orElse(null);

        if (baseCategoryModel == null) {
            log.error("Failed to find BaseCategory with ID: {}", baseCategoryId);
            return ApiResponse.failure(BASE_CATEGORY_NOT_FOUND);
        }

        SubCategoryModel subCategoryModel = subCategoryRepository.findById(subCategoryId)
                .orElse(null);

        if (subCategoryModel == null) {
            log.error("Failed to find SubCategory with ID: {}", subCategoryId);
            return ApiResponse.failure(SUB_CATEGORY_NOT_FOUND);
        }

        List<SubCategoryModel> subCategoryModelList = baseCategoryModel.getSubCategoryModelList();
        if (!subCategoryModelList.remove(subCategoryModel)) {
            log.error("SubCategory with ID: {} is not associated with BaseCategory with ID: {}", subCategoryId, baseCategoryId);
            return ApiResponse.failure(SUB_CATEGORY_NOT_ASSOCIATED);
        }

        baseCategoryModel.setSubCategoryModelList(subCategoryModelList);
        baseCategoryRepository.save(baseCategoryModel);

        return ApiResponse.success(REMOVE_SUB_CATEGORY, baseCategoryMapper.toResponse(baseCategoryModel));
    }


    public ApiResponse<BaseCategoryResponseDto> updateBaseCategory(Long baseCategoryId, BaseCategoryUpdateRequest baseCategoryUpdateRequest) {

        BaseCategoryModel baseCategoryModel = baseCategoryRepository.findById(baseCategoryId)
                .orElse(null);

        if (baseCategoryModel == null) {
            log.error("Failed to update BaseCategory with ID: {}", baseCategoryId);
            return ApiResponse.failure(BASE_CATEGORY_NOT_UPDATED);
        }

        if (!baseCategoryModel.isVisible()) {
            log.error("Failed to update BaseCategory with ID: {} - Category not visible", baseCategoryId);
            return ApiResponse.failure(BASE_CATEGORY_NOT_FOUND);
        }

        if (baseCategoryUpdateRequest.getName() != null) {
            baseCategoryModel.setName(baseCategoryUpdateRequest.getName());
        }

        baseCategoryModel.setUpdatedDateTime(ZonedDateTime.now());

        BaseCategoryModel updatedModel = baseCategoryRepository.save(baseCategoryModel);
        return ApiResponse.success(BASE_CATEGORY_UPDATED_SUCCESFULLY, baseCategoryMapper.toResponse(updatedModel));
    }


}
