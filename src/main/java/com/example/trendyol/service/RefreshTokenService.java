package com.example.trendyol.service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import com.example.trendyol.dto.response.UserResponseDto;
import com.example.trendyol.model.postgres.RefreshToken;
import com.example.trendyol.model.postgres.UserModel;
import com.example.trendyol.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Value("${refresh.token.expires.in}")
    Long expireSeconds;

    private final RefreshTokenRepository refreshTokenRepository;


    public String createRefreshToken(UserModel user) {
        RefreshToken token = refreshTokenRepository.findByUserId(user.getId());
        if(token == null) {
            token =	new RefreshToken();
            token.setUser(user);
        }
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + 1000 * 60 * 60 * 24);//1 day
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(Date.from(Instant.now().plusSeconds(expireSeconds)));
        refreshTokenRepository.save(token);
        return token.getToken();
    }

    public boolean isRefreshExpired(RefreshToken token) {
        return token.getExpiryDate().before(new Date());
    }

    public RefreshToken getByUser(Long userId) {
        System.out.println(userId);
        return refreshTokenRepository.findByUserId(userId);
    }

}