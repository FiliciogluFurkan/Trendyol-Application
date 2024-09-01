package com.example.trendyol.dto.request;


import lombok.Data;
@Data
public class RefreshRequest {

    Long userId;
    String refreshToken;
}