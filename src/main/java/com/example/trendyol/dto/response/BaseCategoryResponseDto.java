package com.example.trendyol.dto.response;

import com.example.trendyol.model.mongo.SubCategoryModel;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BaseCategoryResponseDto {

    private Long id;
    private String name;
    private ZonedDateTime createdDateTime;
    private ZonedDateTime updatedDateTime;
    private List<SubCategoryModel> subCategoryModelList;


}
