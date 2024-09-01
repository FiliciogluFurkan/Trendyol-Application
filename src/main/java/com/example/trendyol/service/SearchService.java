package com.example.trendyol.service;

import com.example.trendyol.dto.response.ApiResponse;
import com.example.trendyol.dto.response.ProductResponseDto;
import com.example.trendyol.mapper.ProductMapper;
import com.example.trendyol.model.elasticsearch.ProductElasticSearchModel;
import com.example.trendyol.repository.SearchProductRepository;
import static com.example.trendyol.validation.ValidationMessages.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchProductRepository searchProductRepository;
    private final ProductMapper productMapper;

    public ApiResponse<List<ProductElasticSearchModel>> getProductsByCustomQuery(String search) {

        Iterable<ProductElasticSearchModel> products = searchProductRepository.findByName(search);

        List<ProductElasticSearchModel> filteredProducts = StreamSupport.stream(products.spliterator(), false)
                .filter(product -> product.isVisible())
                .collect(Collectors.toList());
        return ApiResponse.success("Products retrieved successfully", filteredProducts);
    }
}
