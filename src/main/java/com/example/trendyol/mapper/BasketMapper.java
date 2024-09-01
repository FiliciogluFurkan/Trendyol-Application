package com.example.trendyol.mapper;

import com.example.trendyol.dto.response.BasketResponseDto;
import com.example.trendyol.model.postgres.BasketModel;
import org.springframework.stereotype.Component;

@Component
public class BasketMapper {


    public BasketResponseDto toResponse(BasketModel basketModel) {

        return BasketResponseDto.builder().
                id(basketModel.getId())
                .userId(basketModel.getUserId())
                .productModelList(basketModel.getProductModelList())
                .build();

    }

}
