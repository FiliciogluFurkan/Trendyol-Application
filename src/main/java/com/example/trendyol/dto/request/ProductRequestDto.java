package com.example.trendyol.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {


    private String name;
    private String imageUrl;
    private String description;
    private double productPrice; // tüm fiyat bilgilrti BigDecimal türünde olacak. BigDecimal nedir?
    private List<Long> subCategoryIds;


}
