package com.example.trendyol.configuration.security;

import com.example.trendyol.model.postgres.UserModel;
import com.example.trendyol.model.roles.RoleModel;
import com.example.trendyol.model.rolesprivileges.PrivilegeModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {


    @Value("${jwt-key}")
    private String SECRET_KEY;

    public String generateJwtToken(Authentication auth, UserModel user) {
        JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal();

        Date now = new Date();
        Date expireDate = new Date(now.getTime() + 1000 * 60 * 60 * 24); // 1 gün

        // Custom claims oluşturma
        Long id = userDetails.getId();
        String userName = userDetails.getUsername();
        Set<String> collectRoles = user.getRoles()
                .stream()
                .map(RoleModel::getName)
                .collect(Collectors.toSet());
        Set<String> collectP = user.getRoles()
                .stream()
                .flatMap(roleModel -> roleModel.getPrivileges().stream())
                .map(PrivilegeModel::getName)
                .collect(Collectors.toSet());
        Map<String, Object> claims = Map.of(
                "user_role", collectRoles,
                "user_id", id,
                "user_name", userName,
                "role_privileges", collectP
        );


        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }


    public String generateJwtTokenByUserModel(UserModel userModel) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + 1000 * 60 * 60 * 24); // 1 gün

        // Custom claims oluşturma

        Long id = userModel.getId();
        String userName = userModel.getUserName();
        Set<String> collectRoles = userModel.getRoles()
                .stream()
                .map(RoleModel::getName)
                .collect(Collectors.toSet());
        Set<String> collectP = userModel.getRoles()
                .stream()
                .flatMap(roleModel -> roleModel.getPrivileges().stream())
                .map(PrivilegeModel::getName)
                .collect(Collectors.toSet());
        Map<String, Object> claims = Map.of(
                "user_role", collectRoles,
                "user_id", id,
                "user_name", userName,
                "role_privileges", collectP
        );


        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public Long getUserIdFromJwt(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            Integer id = claims.get("user_id", Integer.class);
            return id != null ? id.longValue() : null;
        } catch (Exception e) {

            throw new RuntimeException("Invalid JWT token", e);
        }
    }

    boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }


}


