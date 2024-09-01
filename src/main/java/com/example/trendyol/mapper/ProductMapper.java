package com.example.trendyol.mapper;

import com.example.trendyol.dto.request.ProductRequestDto;
import com.example.trendyol.dto.response.ProductResponseDto;
import com.example.trendyol.model.elasticsearch.ProductElasticSearchModel;
import com.example.trendyol.model.postgres.ProductModel;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class ProductMapper {

    public ProductModel toModel(ProductRequestDto productRequestDto) {

        return ProductModel.builder()
                .name(productRequestDto.getName())
                .description(productRequestDto.getDescription())
                .imageUrl(productRequestDto.getImageUrl())
                .createdDateTime(ZonedDateTime.now())
                .updatedDateTime(ZonedDateTime.now())
                .productPrice(productRequestDto.getProductPrice())
                .visible(true)
                .build();
    }

    public ProductResponseDto toResponse(ProductModel productModel) {

        return ProductResponseDto.builder()
                .name(productModel.getName())
                .id(productModel.getId())
                .createDateTime(productModel.getCreatedDateTime())
                .updatedDateTime(productModel.getUpdatedDateTime())
                .description(productModel.getDescription())
                .imageUrl(productModel.getImageUrl())
                .productPrice(productModel.getProductPrice())
                .subCategoryModels(productModel.getSubCategories())
                .userId(productModel.getUserId())
                .build();

    }

    public ProductElasticSearchModel toElastic(ProductModel productModel){

        return ProductElasticSearchModel.builder()
                .name(productModel.getName())
                .id(productModel.getId())
                .description(productModel.getDescription())
                .imageUrl(productModel.getImageUrl())
                .visible(productModel.isVisible())
                .productPrice(productModel.getProductPrice())
                .build();


    }

}
