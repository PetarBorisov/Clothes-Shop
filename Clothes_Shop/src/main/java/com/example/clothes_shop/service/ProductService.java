package com.example.clothes_shop.service;

import com.example.clothes_shop.dto.ProductAddDTO;
import com.example.clothes_shop.dto.ProductEditDTO;
import com.example.clothes_shop.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    void addProduct(ProductAddDTO productAddDTO);

    ProductEditDTO getProductById(Long id);

    ProductEditDTO getProductEditDtoById(Long id);

    void saveProduct(ProductEditDTO productEditDTO);

    void editProduct(Long id, ProductEditDTO productEditDTO);

    void deleteProduct(Long id);
}
