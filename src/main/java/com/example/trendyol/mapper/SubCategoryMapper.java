package com.example.trendyol.mapper;

import com.example.trendyol.dto.request.SubCategoryRequestDto;
import com.example.trendyol.dto.response.SubCategoryResponseDto;
import com.example.trendyol.model.mongo.SubCategoryModel;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class SubCategoryMapper {

    public SubCategoryModel toModel(SubCategoryRequestDto subCategoryRequestDto){

        return SubCategoryModel.builder()
                .updatedDateTime(ZonedDateTime.now())
                .createdDateTime(ZonedDateTime.now())
                .name(subCategoryRequestDto.getName())
                .visible(true)
                .build();

    }

    public SubCategoryResponseDto toResponse(SubCategoryModel subCategoryModel){
        return SubCategoryResponseDto.builder()
                .id(subCategoryModel.getId())
                .updatedDateTime(subCategoryModel.getUpdatedDateTime())
                .createdDateTime(subCategoryModel.getCreatedDateTime())
                .name(subCategoryModel.getName())
                .baseCategory(subCategoryModel.getBaseCategory())
                .products(subCategoryModel.getProducts())
                .build();

    }

}
