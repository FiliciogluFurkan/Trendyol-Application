package com.example.trendyol.controller;


import com.example.trendyol.data.ApiPaths;
import com.example.trendyol.dto.response.ApiResponse;
import com.example.trendyol.model.elasticsearch.ProductElasticSearchModel;
import com.example.trendyol.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.SEARCH)
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/{search}")
    public ResponseEntity<ApiResponse<List<ProductElasticSearchModel>>> getProductsByCustomQuery(@PathVariable String search) {

        ApiResponse<List<ProductElasticSearchModel>> response = searchService.getProductsByCustomQuery(search);

        return response.isSuccess() ?
                ResponseEntity.ok(response) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


}
