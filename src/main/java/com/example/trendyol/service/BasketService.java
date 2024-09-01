package com.example.trendyol.service;


import com.example.trendyol.configuration.security.JwtTokenProvider;
import com.example.trendyol.dto.response.ApiResponse;
import com.example.trendyol.dto.response.BasketResponseDto;
import com.example.trendyol.mapper.BasketMapper;
import com.example.trendyol.model.postgres.BasketModel;
import com.example.trendyol.model.postgres.ProductModel;
import com.example.trendyol.model.postgres.UserModel;
import com.example.trendyol.repository.BasketRepository;
import com.example.trendyol.repository.ProductRepository;
import com.example.trendyol.repository.UserRepository;
import com.example.trendyol.service.kafka.EmailProducerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.trendyol.validation.ValidationMessages.*;

@Service
@RequiredArgsConstructor
public class BasketService {

    private static final Logger log = LoggerFactory.getLogger(BasketService.class);
    private final BasketRepository basketRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final BasketMapper basketMapper;
    private final JwtTokenProvider jwtTokenProvider;
    //private final MailService mailService;
    private final EmailProducerService emailProducerService;

    public ApiResponse<BasketResponseDto> assignProductToUser(HttpServletRequest request, Long productId) {

        String token = request.getHeader("Authorization").substring(7);
        Long userId = jwtTokenProvider.getUserIdFromJwt(token);

        UserModel user = userRepository.findById(userId).orElse(null);
        ProductModel product = productRepository.findById(productId).orElse(null);

        if (user == null && product == null) {
            log.error("User with id: {} and product with id: {} are not found", userId, productId);
            return ApiResponse.failure(USER_AND_PRODUCT_NOT_FOUND);
        }

        if (user == null) {
            log.error("User with id {} is not found", userId);
            return ApiResponse.failure(USER_NOT_FOUND);
        }

        if (product == null) {
            log.error("Product with id {} is not found", productId);
            return ApiResponse.failure(PRODUCT_NOT_FOUND);
        }


        BasketModel basket = basketRepository.findByUserId(userId)
                .orElseGet(() -> {
                    BasketModel newBasket = new BasketModel();
                    newBasket.setUserId(userId);
                    newBasket.setProductModelList(new ArrayList<>());
                    return newBasket;
                });


        if (!basket.getProductModelList().contains(product)) {
            basket.getProductModelList().add(product);
        }

        basketRepository.save(basket);

        List<BasketModel> basketModelList = new ArrayList<>();
        basketModelList.add(basket);
        product.setBasketEntityList(basketModelList);
        productRepository.save(product);


        //send mail to product owner
        Long ownerIdOfProduct=product.getUserId();
        UserModel owner=userRepository.findById(ownerIdOfProduct).get();
        String bodyText="A user assigned a product  with name "+product.getName()+" by "+user.getName();
     //   mailService.sendMail(owner.getEmail(),"Product",bodyText);
        emailProducerService.sendEmail(owner.getEmail(),"Product",bodyText);
        return ApiResponse.success(PRODUCT_ASSIGNED_SUCCESFULLY, basketMapper.toResponse(basket));
    }


    public ApiResponse<BasketResponseDto> removeProductFromUser(HttpServletRequest request, Long productId) {
        String token = request.getHeader("Authorization").substring(7);
        Long userId = jwtTokenProvider.getUserIdFromJwt(token);

        UserModel user = userRepository.findById(userId).orElse(null);
        ProductModel product = productRepository.findById(productId).orElse(null);

        if (user == null && product == null) {
            log.error("User with id: {} and product with id: {} are not found", userId, productId);
            return ApiResponse.failure(USER_AND_PRODUCT_NOT_FOUND);
        }

        if (user == null) {
            log.error("User with id {} is not found", userId);
            return ApiResponse.failure(USER_NOT_FOUND);
        }

        if (product == null) {
            log.error("Product with id {} is not found", productId);
            return ApiResponse.failure(PRODUCT_NOT_FOUND);
        }

        BasketModel basket = basketRepository.findByUserId(userId).orElse(null);

        if (basket == null || !basket.getProductModelList().contains(product)) {
            log.error("User with id {} has no product in basket", userId);
            return ApiResponse.failure(BASKET_NOT_INCLUDE_PRODUCT);
        }

        basket.getProductModelList().remove(product);
        basketRepository.save(basket);

        product.getBasketEntityList().remove(basket);
        productRepository.save(product);

        return ApiResponse.success(PRODUCT_REMOVED_SUCCESFULLY, basketMapper.toResponse(basket));
    }


    public ApiResponse<List<BasketResponseDto>> getAllBaskets() {

        List<BasketResponseDto> listOfBaskets = basketRepository.findAll()
                .stream().map(basketModel -> basketMapper.toResponse(basketModel)).toList();

        return ApiResponse.success(BASKETS_RETRIEVED_SUCCESFULLY, listOfBaskets);


    }

    public ApiResponse<BasketResponseDto> getBasket(Long userId) {

        Optional<BasketModel> optionalBasketModel = basketRepository.findById(userId);
        if (optionalBasketModel.isEmpty()) {
            return ApiResponse.failure(BASKET_NOT_FOUND);
        }
        BasketModel basketModel = optionalBasketModel.get();
        return ApiResponse.success(BASKET_RETRIEVED_SUCCESFULLY_, basketMapper.toResponse(basketModel));
    }
}
