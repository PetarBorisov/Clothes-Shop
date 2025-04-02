package com.example.clothes_shop.repository;

import com.example.clothes_shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product ,Long> {

}
