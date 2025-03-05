package com.example.clothes_shop.service;

import com.example.clothes_shop.dto.UserRegisterDTO;
import com.example.clothes_shop.entity.UserEntity;

import java.util.List;

public interface UserService {

    boolean registerUser(UserRegisterDTO userRegisterDTO);

    void promoteToAdmin(Long userId);

    List<UserEntity> getAllUsers();

    void removeAdminRole(Long userId);
}
