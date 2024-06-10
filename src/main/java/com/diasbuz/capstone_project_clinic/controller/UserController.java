package com.diasbuz.capstone_project_clinic.controller;

import com.diasbuz.capstone_project_clinic.model.User;
import com.diasbuz.capstone_project_clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/profile")
    public String profile(Model model, Authentication authentication) {
        Optional<User> user = userService.findByLogin(authentication.getName());
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        model.addAttribute("user", user);
        model.addAttribute("role", role.replace("ROLE_", "").toLowerCase());
        return "profile";
    }

    @PostMapping("/update-profile")
    public String updateProfile(User user, Authentication authentication) {
        Optional<User> existingUser = userService.findByLogin(authentication.getName());
        existingUser.get().setName(user.getName());
        existingUser.get().setPhone(user.getPhone());
        existingUser.get().setEmail(user.getEmail());
        userService.saveUser(existingUser.orElse(null));
        return "redirect:/profile";
    }

    @PostMapping("/register")
    public String register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/login";
    }
}
