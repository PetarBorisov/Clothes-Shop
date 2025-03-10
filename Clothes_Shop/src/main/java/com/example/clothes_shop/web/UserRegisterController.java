package com.example.clothes_shop.web;

import com.example.clothes_shop.dto.UserRegisterDTO;
import com.example.clothes_shop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

@RequestMapping("users")
@Controller
public class UserRegisterController {

    private final UserService userService;
    //private final EmailService emailService;

    public UserRegisterController(UserService userService) {
        this.userService = userService;


    }

    @GetMapping("/register")
    public String register(@ModelAttribute("userRegisterDTO") UserRegisterDTO userRegisterDTO) {
        return "auth-register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("userRegisterDTO") @Valid UserRegisterDTO userRegisterDTO,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "auth-register";
        }

        boolean hasSuccessfulRegistration = userService.registerUser(userRegisterDTO);

        if (!hasSuccessfulRegistration) {
            redirectAttributes.addFlashAttribute("hasRegisterError", true);
            return "redirect:/users/register";

        }
        return "auth-login";
       /* emailService.sendRegistrationEmail(userRegisterDTO.getEmail(), userRegisterDTO.getFirstName(), Locale.ENGLISH);

        redirectAttributes.addFlashAttribute("successMessage", "Регистрацията беше успешна. Моля, влезте в профила си.");
        return "redirect:/login";*/

        }
}