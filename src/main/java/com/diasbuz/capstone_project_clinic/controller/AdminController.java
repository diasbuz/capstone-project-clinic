package com.diasbuz.capstone_project_clinic.controller;

import com.diasbuz.capstone_project_clinic.model.Service;
import com.diasbuz.capstone_project_clinic.model.User;
import com.diasbuz.capstone_project_clinic.repository.ServiceRepository;
import com.diasbuz.capstone_project_clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping
    public String adminPage(Model model) {
        List<User> users = userService.findAll();
        List<Service> services = serviceRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("services", services);
        return "admin";
    }

    @PostMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping("/add-doctor")
    public String addDoctor(@ModelAttribute User doctor) {
        userService.saveUser(doctor);
        return "redirect:/admin";
    }
}

