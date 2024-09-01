package com.example.trendyol.service;

import com.example.trendyol.dto.request.UserRequestDto;
import com.example.trendyol.dto.response.ApiResponse;
import com.example.trendyol.dto.response.ProductResponseDto;
import com.example.trendyol.dto.response.UserResponseDto;
import com.example.trendyol.mapper.PrivilegeMapper;
import com.example.trendyol.mapper.ProductMapper;
import com.example.trendyol.mapper.RoleMapper;
import com.example.trendyol.mapper.UserMapper;
import com.example.trendyol.model.postgres.BasketModel;
import com.example.trendyol.model.postgres.ProductModel;
import com.example.trendyol.model.postgres.UserModel;
import com.example.trendyol.model.update.UserUpdateRequest;
import com.example.trendyol.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.trendyol.validation.ValidationMessages.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final RoleMapper roleMapper;
    private final PrivilegeMapper privilegeMapper;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BasketRepository basketRepository;
    private final ProductMapper productMapper;

    public ApiResponse<UserModel> addUser(UserRequestDto userRequestDto) {

        if (userRepository.existsByPhoneNumber(userRequestDto.getPhoneNumber()) || userRepository.existsByUserName(userRequestDto.getUsername()) || userRepository.existsByEmail(userRequestDto.getEmail()) || userRepository.existsByIdentityNumber(userRequestDto.getIdentityNumber())) {
            UserModel userModel = userRepository.findByUserNameAndVisible(userRequestDto.getUsername(), true);
            if (userModel != null && userModel.isVisible()) {
                log.error("This user have already added ");
                return ApiResponse.failure(USER_ALREADY_EXISTS);
            }

        }

        return ApiResponse.success(USER_ADDED_SUCCESFULLY, userRepository.save(userMapper.toModel(userRequestDto)));
    }

    @Transactional
    public ApiResponse<Void> deleteUser(Long userId) {

        UserModel userModel = userRepository.findById(userId).orElse(null);
        if (userModel == null || !userModel.isVisible()) {
            log.info("User not found so not deleted");
            return ApiResponse.failure(USER_NOT_FOUND);
        }

        log.info("User deleted successfully with Id {}", userId);
        userModel.setVisible(false);
        userRepository.save(userModel);

        refreshTokenRepository.deleteByUserId(userModel.getId());


        return ApiResponse.success("User deleted successfully");

    }


    public ApiResponse<UserResponseDto> getUser(Long userId) {

        UserModel userModel = userRepository.findById(userId).orElse(null);
        if (userModel == null || !userModel.isVisible()) {
            log.error("User not found with id {}", userId);
            return ApiResponse.failure(USER_NOT_FOUND);
        }

        return ApiResponse.success("User retrieved succesfully", userMapper.toResponse(userModel));

    }


    public ApiResponse<List<UserResponseDto>> getUsers() {

        List<UserResponseDto> listOfUserDtos = userRepository.findAll()
                .stream()
                .filter(userModel -> Objects.equals(Boolean.TRUE, userModel.isVisible()))
                .map(userMapper::toResponse).toList();

        return ApiResponse.success(USERS_RETRIEVED_SUCCESFULLY, listOfUserDtos);
    }

    public ApiResponse<UserResponseDto> updateUser(Long userId, UserUpdateRequest userUpdateRequest) {

        UserModel userModel = userRepository.findById(userId)
                .orElse(null);

        if (userModel == null || !userModel.isVisible()) {
            log.error("User not found with id {}", userId);
            return ApiResponse.failure(USER_NOT_FOUND);
        }

        if (userUpdateRequest.getUserName() != null) {
            userModel.setUserName(userUpdateRequest.getUserName());
        }
        if (userUpdateRequest.getEmail() != null) {
            userModel.setEmail(userUpdateRequest.getEmail());
        }
        if (userUpdateRequest.getPassword() != null) {
            userModel.setPassword(userUpdateRequest.getPassword());
        }
        if (userUpdateRequest.getPhoneNumber() != null) {
            userModel.setPhoneNumber(userUpdateRequest.getPhoneNumber());
        }
        userModel.setUpdatedDateTime(ZonedDateTime.now());

        UserResponseDto responseDto = userMapper.toResponse(userRepository.save(userModel));
        log.info("User with id {} updated successfully", userId);
        return ApiResponse.success(USER_UPDATED_SUCCESFULLY, responseDto);
    }


    public UserModel getOneUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public ApiResponse<List<ProductResponseDto>> getProductsOfUser(Long userId) {

        UserModel userModel = userRepository.findById(userId).orElse(null);
        if (userModel == null) {
            log.error("user not found with id {}", userId);
            return ApiResponse.failure(USER_NOT_FOUND);
        }


        BasketModel basketModel = basketRepository.findByUserId(userId).orElse(null);

        if (basketModel == null) {
            return ApiResponse.success(USER_HAS_NO_PRODUCT, new ArrayList<>());
        }

        List<ProductResponseDto> listOfProducts = basketModel.getProductModelList().stream()
                .filter(productModel -> Objects.equals(Boolean.TRUE, productModel.isVisible()))
                .map(productMapper::toResponse).toList();

        return ApiResponse.success(PRODUCTS_RETRIEVED_SUCCESFULLY, listOfProducts);

    }

}
