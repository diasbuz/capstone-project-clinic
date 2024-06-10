package com.diasbuz.capstone_project_clinic.controller;

import com.diasbuz.capstone_project_clinic.model.Doctor;
import com.diasbuz.capstone_project_clinic.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", adminService.findAllUsers());
        return "admin/users";
    }

    @PostMapping("/users/delete")
    public String deleteUser(Integer userId) {
        adminService.deleteUser(userId);
        return "redirect:/admin/users";
    }

    @GetMapping("/doctors/new")
    public String newDoctorForm(Model model) {
        model.addAttribute("services", adminService.findAllServices());
        return "admin/new-doctor";
    }

    @PostMapping("/doctors/new")
    public String addDoctor(Doctor doctor) {
        adminService.saveDoctor(doctor);
        return "redirect:/admin/users";
    }
}

