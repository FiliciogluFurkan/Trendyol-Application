package com.example.trendyol.mapper;

import com.example.trendyol.dto.request.BaseCategoryRequestDto;
import com.example.trendyol.dto.response.BaseCategoryResponseDto;
import com.example.trendyol.model.mongo.BaseCategoryModel;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class BaseCategoryMapper {

public BaseCategoryModel toModel(BaseCategoryRequestDto baseCategoryRequestDto){

    ZonedDateTime now = ZonedDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
    String formattedDate = now.format(formatter);

    return BaseCategoryModel.builder()
            .name(baseCategoryRequestDto.getName())
            .updatedDateTime(ZonedDateTime.now())
            .createdDateTime(ZonedDateTime.now())
            .subCategoryModelList(null)
            .visible(true)
            .build();

}

public BaseCategoryResponseDto toResponse(BaseCategoryModel baseCategoryModel){



    return BaseCategoryResponseDto.builder()
            .id(baseCategoryModel.getId())
            .updatedDateTime(baseCategoryModel.getUpdatedDateTime())
            .createdDateTime(baseCategoryModel.getCreatedDateTime())
            .name(baseCategoryModel.getName())
            .subCategoryModelList(baseCategoryModel.getSubCategoryModelList())
            .build();
}
}
