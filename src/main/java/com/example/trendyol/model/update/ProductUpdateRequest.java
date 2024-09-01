package com.example.trendyol.model.update;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequest {

    @Size(min = 3, max = 70, message = "name must be between 3 and 70 character")
    private String name;


    @Size(min = 5, max = 500)
    private String description;


    @Size(min = 5, max = 70, message = "image url must be between 5 and 70 character")
    private String imageUrl;

    @Positive
    private double productPrice;
}
