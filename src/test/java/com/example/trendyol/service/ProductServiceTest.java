/*package com.example.trendyol.service;

import com.example.trendyol.dto.request.ProductRequestDto;
import com.example.trendyol.dto.response.ApiResponse;
import com.example.trendyol.dto.response.ProductResponseDto;
import com.example.trendyol.mapper.ProductMapper;
import com.example.trendyol.model.mongo.SubCategoryModel;
import com.example.trendyol.model.postgres.ProductModel;
import com.example.trendyol.repository.ProductRepository;
import com.example.trendyol.repository.SubCategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static com.example.trendyol.validation.ValidationMessages.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    ProductRepository productRepository;

    @Mock
    ProductMapper productMapper;

    @Mock
    SubCategoryRepository subCategoryRepository;

    @Mock
    Logger log;

    @InjectMocks
    ProductService underTest;


    @Test
    public void addProduct_whenProductAlreadyExists_shouldReturnFailure() {
        // Arrange
        ProductRequestDto productRequestDto = new ProductRequestDto();

        when(productRepository.existsByName(productRequestDto.getName())).thenReturn(true);

        ApiResponse<ProductResponseDto> expectedResult=ApiResponse.failure(PRODUCT_ALREADY_EXISTS);
        // Act
       ApiResponse<ProductResponseDto> actualResult = underTest.addProduct(productRequestDto);

        assertEquals(expectedResult.getData(),actualResult.getData());
        assertEquals(expectedResult.getMessage(),actualResult.getMessage());
        assertEquals(expectedResult.isSuccess(),actualResult.isSuccess());

    }

    @Test
    public void addProduct_whenSubCategoryNotFound_shouldReturnFailure() {
        // Arrange
        ProductRequestDto productRequestDto = new ProductRequestDto();
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        productRequestDto.setSubCategoryIds(ids);

        when(productRepository.existsByName(productRequestDto.getName())).thenReturn(false);
        when(subCategoryRepository.findById(1L)).thenReturn(Optional.empty());

        ApiResponse<ProductResponseDto> expectedResult= ApiResponse.failure(SUB_CATEGORY_NOT_FOUND);
        ApiResponse<ProductResponseDto> actualResult = underTest.addProduct(productRequestDto);

       assertEquals(expectedResult.getMessage(),actualResult.getMessage());
       assertEquals(expectedResult.isSuccess(),actualResult.isSuccess());
       assertEquals(expectedResult.getData(),actualResult.getData());


    }

    @Test
    public void addProduct_whenValidRequest_shouldReturnSuccess() {
        // Arrange
        SubCategoryModel subCategory = new SubCategoryModel();
        subCategory.setId(1L);

        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setName("Test Product");
        productRequestDto.setSubCategoryIds(Arrays.asList(1L)); // Subcategory ID'leri ayarlayın

        ProductModel productModel = new ProductModel();
        List<SubCategoryModel> subCategoryModels = new ArrayList<>();
        subCategoryModels.add(subCategory);

        ProductResponseDto productResponseDto = new ProductResponseDto();

        // Mock ayarları
        when(productRepository.existsByName(productRequestDto.getName())).thenReturn(false);
        when(subCategoryRepository.findById(1L)).thenReturn(Optional.of(subCategory));
        when(productMapper.toModel(productRequestDto)).thenReturn(productModel);
        productModel.setSubCategories(subCategoryModels); // Bu ayarı yapın
        when(productRepository.save(productModel)).thenReturn(productModel);
        when(productMapper.toResponse(productModel)).thenReturn(productResponseDto);

        ApiResponse<ProductResponseDto> expectedResult = ApiResponse.success(PRODUCT_ADDED_SUCCESFULLY, productResponseDto);

        // Act
        ApiResponse<ProductResponseDto> actualResult = underTest.addProduct(productRequestDto);

        // Assert
        assertEquals(expectedResult.isSuccess(), actualResult.isSuccess());
        assertEquals(expectedResult.getData(), actualResult.getData());
        assertEquals(expectedResult.getMessage(), actualResult.getMessage());
    }

    @Test
    void getProducts_whenProductsExist_shouldReturnSuccess() {
        // Arrange
        ProductModel visibleProduct = new ProductModel();
        visibleProduct.setVisible(true);
        ProductModel hiddenProduct = new ProductModel();
        hiddenProduct.setVisible(false);

        ProductResponseDto visibleProductResponseDto = new ProductResponseDto();

        List<ProductModel> productList = new ArrayList<>();
        productList.add(visibleProduct);
        productList.add(hiddenProduct);

        when(productRepository.findAll()).thenReturn(productList);
        when(productMapper.toResponse(visibleProduct)).thenReturn(visibleProductResponseDto);

        List<ProductResponseDto> expectedProductResponseDtos = List.of(visibleProductResponseDto);

        ApiResponse<List<ProductResponseDto>> expectedResponse = ApiResponse.success(
                "products retrieved succesfully",
                expectedProductResponseDtos
        );

        // Act
        ApiResponse<List<ProductResponseDto>> actualResponse = underTest.getProducts();

        // Assert
        assertEquals(expectedResponse.isSuccess(), actualResponse.isSuccess());
        assertEquals(expectedResponse.getData(), actualResponse.getData());
        assertEquals(expectedResponse.getMessage(), actualResponse.getMessage());
    }

    @Test
    public void deleteProduct_whenProductNotFound_shouldReturnFailure(){

        Long productId=1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        ApiResponse<Void> expectedResult=ApiResponse.failure(PRODUCT_NOT_FOUND);
        ApiResponse<Void> actualResult=underTest.deleteProduct(productId);

     assertEquals(expectedResult.getMessage(),actualResult.getMessage());
     assertEquals(expectedResult.getData(),actualResult.getData());
     assertEquals(expectedResult.isSuccess(),actualResult.isSuccess());

    }

    @Test
    public void deleteProduct_whenProductFound_shouldReturnSuccess() {
        Long productId = 1L;
        ProductModel productModel = new ProductModel();
        productModel.setVisible(true);

        when(productRepository.findById(productId)).thenReturn(Optional.of(productModel));

        //productModel.setVisible(false);
        when(productRepository.save(productModel)).thenReturn(productModel);

        ApiResponse<Void> expectedResult = ApiResponse.success("Product deleted succesfully");
        ApiResponse<Void> actualResult = underTest.deleteProduct(productId);

        assertEquals(expectedResult.isSuccess(), actualResult.isSuccess());
        assertEquals(expectedResult.getData(), actualResult.getData());
        assertEquals(expectedResult.getMessage(), actualResult.getMessage());
    }


}

/*


    public ApiResponse<Void> deleteProduct(Long ProductId) {

        ProductModel productModel = productRepository.findById(ProductId).orElse(null);
        if (productModel == null || !productModel.isVisible()) {
            log.info("Failed to delete product with Id {}", ProductId);
            return ApiResponse.failure(PRODUCT_NOT_FOUND);
        }

        log.info("product deleted succesfully with Id {}", ProductId);
        productModel.setVisible(false);
        productRepository.save(productModel);

        return ApiResponse.success("Product deleted succesfully");

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
        return ApiResponse.success("Product updated successfully", responseDto);
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
        if(subCategoryModels.contains(subCategoryModel)){
            return ApiResponse.failure(SUB_CATEGORY_ALREADY_ADDED);
        }
        subCategoryModels.add(subCategoryModel);
        productModel.setSubCategories(subCategoryModels);

        return ApiResponse.success("Category assigned succesfully", productMapper.toResponse(productRepository.save(productModel)));
    }
}

* */