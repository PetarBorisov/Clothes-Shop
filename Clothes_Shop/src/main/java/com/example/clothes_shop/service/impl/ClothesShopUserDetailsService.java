package com.example.clothes_shop.service.impl;

import com.example.clothes_shop.entity.UserEntity;
import com.example.clothes_shop.entity.UserRoleEntity;
import com.example.clothes_shop.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ClothesShopUserDetailsService implements UserDetailsService {


    private UserRepository userRepository;

    public ClothesShopUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .map(ClothesShopUserDetailsService::map)
                .orElseThrow(() -> new UsernameNotFoundException("User " + email + " not found!"));
    }
    private static UserDetails map(UserEntity userEntity) {
        return User
                .withUsername(userEntity.email())
                .password(userEntity.password())
                .authorities(userEntity.getRoles().stream().map(ClothesShopUserDetailsService::map).toList())
                .build();
    }
    private static GrantedAuthority map(UserRoleEntity userRoleEntity) {
        return new SimpleGrantedAuthority(
                "ROLE_" + userRoleEntity.getRole().name()
        );
    }
    }
