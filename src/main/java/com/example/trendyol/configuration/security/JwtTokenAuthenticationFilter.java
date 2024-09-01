package com.example.trendyol.configuration.security;

import com.example.trendyol.model.postgres.UserModel;
import com.example.trendyol.model.roles.RoleModel;
import com.example.trendyol.repository.UserRepository;
import com.example.trendyol.service.UserDetailServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailServiceImpl userDetailService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwtToken = extractJwtFromRequest(request);

            if (StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)) {
                Long id = jwtTokenProvider.getUserIdFromJwt(jwtToken);
                UserDetails user = userDetailService.loadUserById(id);
                UserModel userModel = userRepository.findById(id).orElse(null);

                if (user != null && userModel != null) {
                    List<GrantedAuthority> authorities = new ArrayList<>();

                    userModel.getRoles().forEach(roleModel -> {
                        authorities.add(new SimpleGrantedAuthority("ROLE_" + roleModel.getName()));

                        roleModel.getPrivileges().forEach(privilege -> {
                            authorities.add(new SimpleGrantedAuthority(privilege.getName()));
                        });
                    });

                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, authorities);
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        } catch (Exception e) {
            logger.error("JWT token validation failed", e);
        }
        filterChain.doFilter(request, response);
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring("Bearer ".length());
        }
        return null;
    }

}
/*
*      try {
            String jwtToken = extractJwtFromRequest(request);
            if (StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)) {
                Long id = jwtTokenProvider.getUserIdFromJwt(jwtToken);
                UserDetails user = userDetailService.loadUserById(id);
                UserModel userModel=userRepository.findById(id).orElse(null);
                if (user != null) {
                    Stream<String> concat = Stream.concat(userModel.getRoles().stream().map(roleModel -> "ROLE_" + roleModel.getName(),
                            userModel.getRoles().stream().map(roleModel ->roleModel.getPrivileges() )
                            ));
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, userModel.);
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }*/