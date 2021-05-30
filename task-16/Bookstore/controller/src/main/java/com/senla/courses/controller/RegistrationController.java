package com.senla.courses.controller;

import com.senla.courses.model.User;
import com.senla.courses.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private static final Logger log = LogManager.getLogger(RegistrationController.class.getName());


    private final IUserService userService;

    @GetMapping()
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping()
    public String addUser(@RequestBody User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "/registration";
        }
        if (!user.getPassword().equals(user.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "/registration";
        }
        if (!userService.save(user)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "/registration";
        }

        return "redirect:/";
    }
}
