package com.example.trendyol.configuration.security;

import com.example.trendyol.service.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenAuthenticationFilter jwtAuthFilter;

    private final UserDetailServiceImpl userService;

    private final PasswordEncoder passwordEncoder;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x -> x.requestMatchers("/auth/**").permitAll())
                .authorizeHttpRequests(x->x.requestMatchers("/search/**").permitAll())

                .authorizeHttpRequests(x -> x.requestMatchers("/role/assign-role/**").hasAuthority("ASSIGN_ROLE_TO_USER_PRIVILEGE"))
                .authorizeHttpRequests(x -> x.requestMatchers("/role/assign-privileges/**").hasAuthority("ASSIGN_PRIVILEGE_TO_ROLE_PRIVILEGE"))
                .authorizeHttpRequests(x -> x.requestMatchers("/role-privilege/**").hasAuthority("GET_ALL_PRIVILEGES_PRIVILEGE"))
                .authorizeHttpRequests(x -> x.requestMatchers("/role/**").hasAuthority("GET_ALL_ROLES_PRIVILEGE"))


                .authorizeHttpRequests(x -> x.requestMatchers("/basket/product-to-basket/**").hasAuthority("PRODUCT_ASSIGN_TO_USER_PRIVILEGE"))
                .authorizeHttpRequests(x -> x.requestMatchers("/basket/remove-product-from-user/**").hasAuthority("PRODUCT_REMOVE_FROM_USER_PRIVILEGE"))
                .authorizeHttpRequests(x -> x.requestMatchers("/basket/get-all/**").hasAuthority("BASKET_GET_PRIVILEGE"))
                .authorizeHttpRequests(x -> x.requestMatchers("/basket/get-by-id/**").hasAuthority("BASKET_GET_BY_ID_PRIVILEGE"))

                .authorizeHttpRequests(x -> x.requestMatchers("/products/add/**").hasAuthority("PRODUCT_ADD_PRIVILEGE"))
                .authorizeHttpRequests(x -> x.requestMatchers("/products/delete/**").hasAuthority("PRODUCT_DELETE_PRIVILEGE"))
                .authorizeHttpRequests(x -> x.requestMatchers("/products/get-all/**").hasAuthority("PRODUCT_GET_ALL_PRIVILEGE"))
                .authorizeHttpRequests(x -> x.requestMatchers("/products/update/**").hasAuthority("PRODUCT_UPDATE_PRIVILEGE"))
                .authorizeHttpRequests(x -> x.requestMatchers("/products/assign-category/**").hasAuthority("PRODUCT_ASSIGN_SUBCATEGORY_PRIVILEGE"))


                .authorizeHttpRequests(x -> x.requestMatchers("/users/delete/**").hasAuthority("USER_DELETE_PRIVILEGE"))
                .authorizeHttpRequests(x -> x.requestMatchers("/users/update/**").hasAuthority("USER_UPDATE_PRIVILEGE"))
                .authorizeHttpRequests(x -> x.requestMatchers("/users/get-all/**").hasAuthority("USER_GET_ALL_PRIVILEGE"))
                .authorizeHttpRequests(x -> x.requestMatchers("/users/get-by-id/**").hasAuthority("USER_GET_PRIVILEGE"))
                .authorizeHttpRequests(x -> x.requestMatchers("/users/get-products-of-user/**").hasAuthority("USER_GET_PRODUCTS_PRIVILEGE"))


                .authorizeHttpRequests((x -> x.requestMatchers("/base-category/**").hasRole("SUPER_USER")))
                .authorizeHttpRequests(x -> x.requestMatchers("/sub-category/**").hasRole("SUPER_USER"))

                .authorizeHttpRequests(x -> x.requestMatchers("/swagger-ui/**").permitAll())
                .authorizeHttpRequests(x -> x.requestMatchers("/v3/api-docs/**").permitAll())
                .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();

    }
}


   /*return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x -> x.requestMatchers("/auth/**").permitAll())
                .authorizeHttpRequests(x -> x.requestMatchers("/basket/**").authenticated())
                .authorizeHttpRequests(x -> x.requestMatchers("/products/**").authenticated())
                .authorizeHttpRequests(x -> x.requestMatchers("/users/**").authenticated())
                .authorizeHttpRequests(x-> x.requestMatchers("/base-category/**").authenticated())
                .authorizeHttpRequests(x-> x.requestMatchers("/sub-category/**").authenticated())
                .authorizeHttpRequests(x->x.requestMatchers("/swagger-ui/**").permitAll())
                .authorizeHttpRequests(x-> x.requestMatchers("/v3/api-docs/**").permitAll())
                .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();*/