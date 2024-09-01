package com.example.trendyol.configuration.security;

import com.example.trendyol.model.postgres.UserModel;
import com.example.trendyol.model.roles.RoleModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class JwtUserDetails implements UserDetails {

    public Long id;
    private String username;
    private String password;
    private Set<RoleModel> listOfRoles;
    private Collection<? extends GrantedAuthority> authorities;


    private JwtUserDetails(Long id, String username, String password,Set<RoleModel> list, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.listOfRoles=list;
        this.authorities = authorities;
    }

    public static JwtUserDetails createUser(UserModel user) {
        return new JwtUserDetails(user.getId(), user.getUserName(), user.getPassword(),user.getRoles(), new ArrayList<>());
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
