package com.example.clothes_shop.service;

import com.example.clothes_shop.dto.CategoryAddDTO;
import com.example.clothes_shop.dto.CategoryEditDTO;
import com.example.clothes_shop.entity.Category;

import java.util.List;

public interface CategoryService {

    void addCategory(CategoryAddDTO categoryDTO);

    CategoryEditDTO getCategoryById(Long id);

    List<Category> getAllCategory();

    void saveCategory(CategoryEditDTO existingCategory);

    CategoryEditDTO getCategoryEditDTOById(Long Id);

    void editCategory(Long id, CategoryEditDTO categoryEditDTO);

    void deleteCategory(Long Id);


}
