package com.example.clothes_shop.service.impl;

import com.example.clothes_shop.dto.CategoryAddDTO;
import com.example.clothes_shop.dto.CategoryEditDTO;
import com.example.clothes_shop.entity.Category;
import com.example.clothes_shop.repository.CategoriesRepository;
import com.example.clothes_shop.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoriesRepository categoriesRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoriesRepository categoriesRepository, ModelMapper modelMapper) {
        this.categoriesRepository = categoriesRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addCategory(CategoryAddDTO categoryAddDTO) {

        Optional<Category> cat = this.categoriesRepository.findByName(categoryAddDTO.getName());

        if (cat.isPresent()) {
            Category existingCategory = cat.get();
            existingCategory.setName(categoryAddDTO.getName());
            existingCategory.setActive(categoryAddDTO.isActive());

            categoriesRepository.save(existingCategory); // Тук просто обновяваме категорията
        } else {
            Category newCategory = modelMapper.map(categoryAddDTO, Category.class);
            categoriesRepository.save(newCategory);
        }
    }

    @Override
    public CategoryEditDTO getCategoryById(Long Id) {

        Optional<Category> categoryOptional = categoriesRepository.findById(Id);
        if (categoryOptional.isPresent()){
            Category category = categoryOptional.get();
            return convertEntityToEditDTO(category);
        }
        return null;
    }

    private CategoryEditDTO convertEntityToEditDTO(Category category) {
        CategoryEditDTO editDTO = new CategoryEditDTO();
        editDTO.setId(category.getId());
        editDTO.setName(category.getName());
        editDTO.setActive(category.isActive());
        return editDTO;
    }

    @Override
    public List<Category> getAllCategory() {
        return categoriesRepository.findAll();
    }

    @Override
    public void saveCategory(CategoryEditDTO existingCategory) {

        Category category = modelMapper.map(existingCategory, Category.class);

        categoriesRepository.save(category);

    }

    @Override
    public CategoryEditDTO getCategoryEditDTOById(Long Id) {
        Optional<Category> categoryEntityOptional = categoriesRepository.findById(Id);
        return categoryEntityOptional.map(categoryEntity -> modelMapper.map(categoryEntity, CategoryEditDTO.class)).orElse(null);
    }

    @Override
    public void editCategory(Long id, CategoryEditDTO categoryEditDTO) {

        Optional<Category> category = this.categoriesRepository.findById(categoryEditDTO.getId());

        if (category.isPresent()){
            Category existingCategory = category.get();
            existingCategory.setName(categoryEditDTO.getName());
            existingCategory.setActive(categoryEditDTO.isActive());

            categoriesRepository.save(existingCategory);
        }

    }

    @Override
    public void deleteCategory(Long Id) {

        Optional<Category> delete = this.categoriesRepository.findById(Id);
        delete.ifPresent(categoriesRepository::delete);
    }
}
