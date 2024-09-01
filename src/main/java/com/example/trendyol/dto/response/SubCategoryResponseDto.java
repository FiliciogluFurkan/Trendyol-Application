package com.example.trendyol.dto.response;


import com.example.trendyol.model.mongo.BaseCategoryModel;
import com.example.trendyol.model.postgres.ProductModel;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class SubCategoryResponseDto {

    private Long id;
    private String name;
    private List<ProductModel> products;
    private ZonedDateTime createdDateTime;
    private ZonedDateTime updatedDateTime;
    private BaseCategoryModel baseCategory;

}


