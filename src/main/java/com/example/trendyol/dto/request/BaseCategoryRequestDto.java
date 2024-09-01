package com.example.trendyol.dto.request;

import com.example.trendyol.dto.response.BaseCategoryResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseCategoryRequestDto {

    private String name;

}
