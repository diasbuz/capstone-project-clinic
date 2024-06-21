package com.diasbuz.capstone_project_clinic.controller;


import com.diasbuz.capstone_project_clinic.model.Roles;
import com.diasbuz.capstone_project_clinic.model.User;
import com.diasbuz.capstone_project_clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showRegistrationForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/save")
    public String registration(@ModelAttribute("user") User user, BindingResult result, Model model){

        User existingUser = userService.findByLogin(user.getLogin());

        if(existingUser != null && existingUser.getLogin() != null && !existingUser.getLogin().isEmpty()){
            result.rejectValue("login", "400", "There is already an account registered with this login");
        }

        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "register";
        }

        user.setRole(Roles.ROLE_PATIENT);
        user.setBalance(BigDecimal.ZERO);
        userService.saveUser(user);
        return "redirect:/login";
    }
}