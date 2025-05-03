package com.example.clothes_shop.web;

import com.example.clothes_shop.entity.Category;
import com.example.clothes_shop.repository.UserRepository;
import com.example.clothes_shop.repository.UserRoleRepository;
import com.example.clothes_shop.service.CategoryService;
import com.example.clothes_shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    public HomeController(UserRepository userRepository, UserRoleRepository userRoleRepository,
                          UserService userService, CategoryService categoryService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        List<Category> myCategories = categoryService.getAllCategory();
        model.addAttribute("myCategories", myCategories);
        return "index"; // връща index.html
    }
}