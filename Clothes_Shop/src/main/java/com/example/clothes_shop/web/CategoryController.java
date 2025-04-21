package com.example.clothes_shop.web;

import com.example.clothes_shop.dto.CategoryAddDTO;
import com.example.clothes_shop.dto.CategoryEditDTO;
import com.example.clothes_shop.entity.Category;
import com.example.clothes_shop.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String showCategoryForm(Model model) {
        model.addAttribute("categoryAddDTO", new CategoryAddDTO());  // Създаваш нов обект CategoryAddDTO
        model.addAttribute("categories", categoryService.getAllCategory());  // Показваш всички категории
        return "index";  // Показваш основната страница
    }

    @PostMapping("/categories")
    public String submitCategoryForm(@ModelAttribute("categoryAddDTO") @Valid CategoryAddDTO categoryAddDTO,
                                     BindingResult result,
                                     Model model) {

        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategory());
            return "index";  // Връщаме обратно с грешките
        }

        // Добавяме новата категория
        categoryService.addCategory(categoryAddDTO);
        return "redirect:/categories";  // След добавяне, пренасочваме към списъка с категории
    }

    @GetMapping("/edit/category/{id}")
    public String editCategoryForm(@PathVariable Long id, Model model) {
        CategoryEditDTO categoryEditDTO = categoryService.getCategoryEditDTOById(id);

        if (categoryEditDTO != null) {
            model.addAttribute("categoryAddDTO", categoryEditDTO); // Връщаш categoryEditDTO за редактиране
        }

        model.addAttribute("categories", categoryService.getAllCategory()); // Показваш всички категории
        return "index";  // Показваш основната страница
    }
    @PostMapping("/edit/category/{id}")
    public String updateCategory(@PathVariable Long id,
                                 @ModelAttribute("categoryAddDTO") @Valid CategoryAddDTO categoryAddDTO,
                                 BindingResult result,
                                 Model model) {

        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategory());
            return "index";  // Връщаш се към същата страница с грешките
        }

        // Създаваш нов CategoryEditDTO, за да обновиш съществуващата категория
        CategoryEditDTO categoryEditDTO = new CategoryEditDTO();
        categoryEditDTO.setId(id);
        categoryEditDTO.setName(categoryAddDTO.getName());
        categoryEditDTO.setActive(categoryAddDTO.isActive());

        // Редактираш категорията
        categoryService.editCategory(id, categoryEditDTO);

        return "redirect:/categories";  // Пренасочваш към страницата със списъка с категории
    }
    @PostMapping("/delete/category/{id}")
    public String deleteCategory(@PathVariable Long id) {
        // Изтриваме категорията по ID
        categoryService.deleteCategory(id);
        return "redirect:/categories";  // Пренасочваме към списъка с категории
    }
}