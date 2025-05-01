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
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ModelAndView showCategories(Model model) {
        List<Category> myCategories = categoryService.getAllCategory();
        model.addAttribute("myCategories", myCategories);
        model.addAttribute("categoryAddDTO", new CategoryAddDTO()); // За формата за добавяне
        return new ModelAndView("categories"); // Показваме страницата categories.html
    }

    @PostMapping("/add/categories")
    public String addCategory(@ModelAttribute("categoryAddDTO") @Valid CategoryAddDTO categoryAddDTO,
                              BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            // Ако има грешки, презареждаме списъка и връщаме categories.html
            List<Category> myCategories = categoryService.getAllCategory();
            model.addAttribute("myCategories", myCategories);
            return "categories";
        }

        categoryService.addCategory(categoryAddDTO);
        return "redirect:/categories"; // След успешно добавяне презареждаме списъка
    }

    @PostMapping("/delete/category/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories"; // След изтриване се връщаме към списъка
    }
}