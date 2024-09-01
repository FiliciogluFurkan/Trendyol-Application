package com.example.trendyol.mapper;


import com.example.trendyol.dto.request.UserRequestDto;
import com.example.trendyol.dto.response.UserResponseDto;
import com.example.trendyol.model.postgres.UserModel;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class UserMapper {

    public UserModel toModel(UserRequestDto userRequestDto) {

        return UserModel.builder()
                .name(userRequestDto.getName())
                .identityNumber(userRequestDto.getIdentityNumber())
                .phoneNumber(userRequestDto.getPhoneNumber())
                .age(userRequestDto.getAge())
                .birthday(userRequestDto.getBirthday())
                .surname(userRequestDto.getSurname())
                .userName(userRequestDto.getUsername())
                .password(userRequestDto.getPassword())
                .email(userRequestDto.getEmail())
                .createdDateTime(ZonedDateTime.now())
                .updatedDateTime(ZonedDateTime.now())
                .visible(true)
                .build();
    }

    public UserResponseDto toResponse(UserModel userModel) {

        return UserResponseDto.builder()
                .Id(userModel.getId())
                .phoneNumber(userModel.getPhoneNumber())
                .identityNumber(userModel.getIdentityNumber())
                .birthday(userModel.getBirthday())
                .email(userModel.getEmail())
                .createDateTime(userModel.getCreatedDateTime())
                .updatedDateTime(userModel.getUpdatedDateTime())
                .name(userModel.getName())
                .surname(userModel.getSurname())
                .username(userModel.getUserName())
                .age(userModel.getAge())
                .roleModels(userModel.getRoles())
                .build();

    }

}



