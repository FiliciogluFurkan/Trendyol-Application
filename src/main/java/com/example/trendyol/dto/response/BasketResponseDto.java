package com.example.trendyol.dto.response;

import com.example.trendyol.model.postgres.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasketResponseDto {

    private Long id;
    private Long userId;
    private List<ProductModel> productModelList;
}
