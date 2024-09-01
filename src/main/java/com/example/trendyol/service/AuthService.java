package com.example.trendyol.service;

import com.example.trendyol.configuration.security.JwtTokenProvider;
import com.example.trendyol.dto.request.RefreshRequest;
import com.example.trendyol.dto.request.UserRequest;
import com.example.trendyol.dto.request.UserRequestDto;
import com.example.trendyol.dto.response.ApiResponse;
import com.example.trendyol.dto.response.AuthResponse;
import com.example.trendyol.dto.response.UserResponseDto;
import com.example.trendyol.mapper.UserMapper;
import com.example.trendyol.model.postgres.RefreshToken;
import com.example.trendyol.model.postgres.UserModel;
import com.example.trendyol.repository.RoleRepository;
import com.example.trendyol.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AuthResponse login(UserRequest loginRequest){


        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        UserModel byUserName = userRepository.findByUserName(loginRequest.getUserName());
        String jwtToken = jwtTokenProvider.generateJwtToken(auth, byUserName);
        UserModel user = userService.getOneUserByUserName(loginRequest.getUserName());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken("Bearer " + jwtToken);
        authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
        authResponse.setUserId(user.getId());
        return  authResponse;
    }


    public ApiResponse<UserResponseDto> register(UserRequestDto userRequestDto) {

        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());
        userRequestDto.setPassword(encodedPassword);

        ApiResponse<UserModel> response = userService.addUser(userRequestDto);
        ApiResponse<UserResponseDto> dtoApiResponse = new ApiResponse<>();
        if(response.getData()==null){
           dtoApiResponse.setData(null);
        }else {
            dtoApiResponse.setData(userMapper.toResponse(response.getData()));
        }
            dtoApiResponse.setMessage(response.getMessage());
            dtoApiResponse.setTimestamp(response.getTimestamp());
            dtoApiResponse.setSuccess(response.isSuccess());

        if(response.isSuccess()){
            refreshTokenService.createRefreshToken(response.getData());
        }

        return dtoApiResponse;
    }

    public ResponseEntity<AuthResponse> createRefresh(RefreshRequest refreshRequest){


        AuthResponse response = new AuthResponse();
        RefreshToken token = refreshTokenService.getByUser(refreshRequest.getUserId());
        System.out.println(token);
        if (token.getToken().equals(refreshRequest.getRefreshToken()) &&
                !refreshTokenService.isRefreshExpired(token)) {

            UserModel user = token.getUser();
            String jwtToken = jwtTokenProvider.generateJwtTokenByUserModel(user);
            response.setMessage("token successfully refreshed.");
            response.setAccessToken("Bearer " + jwtToken);
            response.setUserId(user.getId());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("refresh token is not valid.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }



    }




}
