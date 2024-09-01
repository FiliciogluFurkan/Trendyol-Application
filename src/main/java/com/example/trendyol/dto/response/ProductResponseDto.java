package com.example.trendyol.dto.response;


import com.example.trendyol.model.mongo.SubCategoryModel;
import com.example.trendyol.model.postgres.BasketModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    private Long id;
    private String name;
    private Long userId;
    private String imageUrl;
    private String description;
    private double productPrice;
    private ZonedDateTime createDateTime;
    private ZonedDateTime updatedDateTime;
    private List<BasketModel> basketEntityList;
    private List<SubCategoryModel> subCategoryModels;


}
