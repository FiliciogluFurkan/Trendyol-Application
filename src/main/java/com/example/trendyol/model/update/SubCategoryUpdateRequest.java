package com.example.trendyol.model.update;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryUpdateRequest {

    @Size(min = 3, max = 40, message = "Base category name's length must be between 3 and 40 character")
    private String name;
}
