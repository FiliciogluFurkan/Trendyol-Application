package com.example.trendyol.service;

import com.example.trendyol.model.postgres.UserModel;
import com.example.trendyol.repository.UserRepository;
import com.example.trendyol.configuration.security.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUserName(username);
        return JwtUserDetails.createUser(user);


    }

    public UserDetails loadUserById(Long id) {
        UserModel user = userRepository.findById(id).get();
        return JwtUserDetails.createUser(user);
    }
}
