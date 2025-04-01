package com.example.clothes_shop.repository;

import com.example.clothes_shop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<Category ,Long > {

    Optional<Category> findByName(String name);
}
