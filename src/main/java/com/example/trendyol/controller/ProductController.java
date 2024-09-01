package com.example.trendyol.controller;


import com.example.trendyol.data.ApiPaths;
import com.example.trendyol.dto.request.ProductRequestDto;
import com.example.trendyol.dto.response.ApiResponse;
import com.example.trendyol.dto.response.ProductResponseDto;
import com.example.trendyol.model.update.ProductUpdateRequest;
import com.example.trendyol.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//localhost:8080/products
@RestController
@RequestMapping(ApiPaths.Products)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<ProductResponseDto>> addProduct(HttpServletRequest request, @RequestBody ProductRequestDto productRequestDto) {

        ApiResponse<ProductResponseDto> response = productService.addProduct(request,productRequestDto);
        return response.isSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }



    @DeleteMapping("/delete/{product-id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable("product-id") Long ProductId) {

        ApiResponse<Void> response = productService.deleteProduct(ProductId);
        return response.isSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> getProducts() {
        ApiResponse<List<ProductResponseDto>> response = productService.getProducts();

        return response.isSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PatchMapping("/update/{product-id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> updateProduct(@PathVariable("product-id") Long ProductId, @RequestBody ProductUpdateRequest productUpdateRequest) {

        ApiResponse<ProductResponseDto> response = productService.updateProduct(ProductId, productUpdateRequest);
        return response.isSuccess() ? ResponseEntity.ok(response) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PatchMapping("/assign-category/{product-id}/{sub-category-id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> assignCategoryToProduct(@PathVariable("product-id") Long productId,@PathVariable("sub-category-id") Long subCategoryId){

        ApiResponse<ProductResponseDto> response=productService.assignCategoryToProduct(productId,subCategoryId);
        return response.isSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }


}


