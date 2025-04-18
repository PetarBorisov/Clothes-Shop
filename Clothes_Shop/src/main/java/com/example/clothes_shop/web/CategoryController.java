package com.example.clothes_shop.web;

import com.example.clothes_shop.dto.CategoryAddDTO;
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
        model.addAttribute("categoryAddDTO", new CategoryAddDTO());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "index";

    }

    @PostMapping("/categories")
    public String submitCategoryForm(@ModelAttribute("categoryAddDTO") @Valid CategoryAddDTO categoryAddDTO,
                                     BindingResult result,
                                     Model model) {

        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategory());
            return "index";
        }

        categoryService.addCategory(categoryAddDTO);
        return "redirect:/categories";
    }
    @PostMapping("/delete/category/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}