package com.example.clothes_shop.repository;

import com.example.clothes_shop.entity.UserRoleEntity;
import com.example.clothes_shop.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    UserRoleRepository findByRole(UserRoleEnum userRoleEnum);

}
