package com.example.trendyol.service;

import com.example.trendyol.configuration.security.JwtTokenProvider;
import com.example.trendyol.dto.request.ProductRequestDto;
import com.example.trendyol.dto.response.ApiResponse;
import com.example.trendyol.dto.response.ProductResponseDto;
import com.example.trendyol.mapper.ProductMapper;
import com.example.trendyol.model.elasticsearch.ProductElasticSearchModel;
import com.example.trendyol.model.mongo.SubCategoryModel;
import com.example.trendyol.model.postgres.ProductModel;
import com.example.trendyol.model.update.ProductUpdateRequest;
import com.example.trendyol.repository.ProductRepository;
import com.example.trendyol.repository.SearchProductRepository;
import com.example.trendyol.repository.SubCategoryRepository;
import com.example.trendyol.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.trendyol.validation.ValidationMessages.*;

@Service
@RequiredArgsConstructor
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SubCategoryRepository subCategoryRepository;
    private final SearchProductRepository searchProductRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public ApiResponse<ProductResponseDto> addProduct(HttpServletRequest request, ProductRequestDto productRequestDto) {

        String token=request.getHeader("Authorization").substring(7);
        Long userId=jwtTokenProvider.getUserIdFromJwt(token);
        System.out.println(userId);


        if (productRepository.existsByName(productRequestDto.getName())) {
            log.error("This product has already been added");
            return ApiResponse.failure(PRODUCT_ALREADY_EXISTS);
        }

        List<Long> subCategoryIds = productRequestDto.getSubCategoryIds();
        if (subCategoryIds == null || subCategoryIds.isEmpty()) {
            log.error("No sub categories provided");
            return ApiResponse.failure(SUB_CATEGORY_NOT_FOUND);
        }

        List<SubCategoryModel> subCategoryModels = new ArrayList<>();
        for (Long id : subCategoryIds) {
            Optional<SubCategoryModel> subCategoryOptional = subCategoryRepository.findById(id);
            if (subCategoryOptional.isPresent()) {
                subCategoryModels.add(subCategoryOptional.get());
            } else {
                log.error("SubCategory with ID: {} not found", id);
                return ApiResponse.failure(SUB_CATEGORY_NOT_FOUND);
            }
        }

        if (subCategoryModels.isEmpty()) {
            log.error("No valid sub categories provided");
            return ApiResponse.failure(SUB_CATEGORY_NOT_FOUND);
        }

        ProductModel productModel = productMapper.toModel(productRequestDto);
        productModel.setSubCategories(subCategoryModels);
        productModel.setUserId(userId);
        ProductModel savedProduct = productRepository.save(productModel);

        searchProductRepository.save(productMapper.toElastic(savedProduct));
        log.info("Product successfully added with ID: {}", savedProduct.getId());
        return ApiResponse.success(PRODUCT_ADDED_SUCCESFULLY, productMapper.toResponse(savedProduct));
    }

    public ApiResponse<List<ProductResponseDto>> getProducts() {
        List<ProductResponseDto> responseDto = productRepository.findAll()
                .stream()
                .filter(productModel -> Objects.equals(Boolean.TRUE, productModel.isVisible()))
                .map(productMapper::toResponse)
                .toList();

        return ApiResponse.success(PRODUCTS_RETRIEVED_SUCCESFULLY, responseDto);
    }

    public ApiResponse<Void> deleteProduct(Long ProductId) {

        ProductModel productModel = productRepository.findById(ProductId).orElse(null);
        if (productModel == null || !productModel.isVisible()) {
            log.info("Failed to delete product with Id {}", ProductId);
            return ApiResponse.failure(PRODUCT_NOT_FOUND);
        }

        log.info("product deleted succesfully with Id {}", ProductId);
        productModel.setVisible(false);
        productRepository.save(productModel);

        ProductElasticSearchModel productElasticSearchModel=productMapper.toElastic(productModel);
        productElasticSearchModel.setVisible(false);
        searchProductRepository.save(productElasticSearchModel);

        return ApiResponse.success(PRODUCT_DELETED_SUCCESFULLY);

    }

    public ApiResponse<ProductResponseDto> updateProduct(Long productId, ProductUpdateRequest productUpdateRequest) {

        ProductModel productModel = productRepository.findById(productId)
                .orElseGet(() -> {
                    log.error("Failed to update Product with ID: {}", productId);
                    return null;
                });

        if (productModel == null || !productModel.isVisible()) {
            log.error("Failed to update Product with ID: {}", productId);
            return ApiResponse.failure("PRODUCT_NOT_UPDATED");
        }

        if (productUpdateRequest.getName() != null) {
            productModel.setName(productUpdateRequest.getName());
        }
        if (productUpdateRequest.getProductPrice() != 0) {
            productModel.setProductPrice(productUpdateRequest.getProductPrice());
        }
        if (productUpdateRequest.getImageUrl() != null) {
            productModel.setImageUrl(productUpdateRequest.getImageUrl());
        }
        if (productUpdateRequest.getDescription() != null) {
            productModel.setDescription(productUpdateRequest.getDescription()); // Image URL yerine Description güncellemesi
        }

        productModel.setUpdatedDateTime(ZonedDateTime.now());

        productRepository.save(productModel);
        ProductResponseDto responseDto = productMapper.toResponse(productModel);
        return ApiResponse.success(PRODUCT_UPDATED_SUCCESFULLY, responseDto);
    }


    public ApiResponse<ProductResponseDto> assignCategoryToProduct(Long productId, Long subCategoryId) {

        ProductModel productModel = productRepository.findById(productId).orElse(null);
        SubCategoryModel subCategoryModel = subCategoryRepository.findById(subCategoryId).orElse(null);

        if (productModel == null) {
            log.error("Product not found with id {}", productId);
            return ApiResponse.failure(PRODUCT_NOT_FOUND);
        }
        if (subCategoryModel == null) {
            log.error("Sub category not found with id {}", subCategoryId);
        }

        List<SubCategoryModel> subCategoryModels = productModel.getSubCategories();
        if (subCategoryModels == null) {
            subCategoryModels = new ArrayList<>();
        }
        if (subCategoryModels.contains(subCategoryModel)) {
            return ApiResponse.failure(SUB_CATEGORY_ALREADY_ADDED);
        }
        subCategoryModels.add(subCategoryModel);
        productModel.setSubCategories(subCategoryModels);

        return ApiResponse.success(CATEGORY_ASSIGNED_SUCCESFULLY, productMapper.toResponse(productRepository.save(productModel)));
    }
}
