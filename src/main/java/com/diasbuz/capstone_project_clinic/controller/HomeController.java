package com.diasbuz.capstone_project_clinic.controller;

import com.diasbuz.capstone_project_clinic.model.Service;
import com.diasbuz.capstone_project_clinic.model.User;
import com.diasbuz.capstone_project_clinic.service.UserService;
import com.diasbuz.capstone_project_clinic.service.serviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private com.diasbuz.capstone_project_clinic.service.serviceService serviceService;

    @GetMapping(value = {"/", "/home"})
    public String home(@AuthenticationPrincipal User user, Model model) {
        if (user != null) {
            model.addAttribute("role", user.getAuthorities().iterator().next().getAuthority());
        } else {
            model.addAttribute("role", null);
        }
        return "home";
    }

    @GetMapping("/specialists")
    public String specialists(Model model) {
        List<User> doctors = userService.findAllDoctors();
        Map<Service, Long> servicesWithDoctorCount = serviceService.getAllServicesWithDoctorCount();
        model.addAttribute("doctors", doctors);
        model.addAttribute("totalDoctors", doctors.size());
        model.addAttribute("specs", servicesWithDoctorCount);
        return "specialists";
    }

    @GetMapping("/comments/{doctorId}")
    public String viewComments(@PathVariable Integer doctorId, Model model) {
        User doctor = userService.findById(doctorId);
        model.addAttribute("doctor", doctor);
        model.addAttribute("comments", doctor.getDoctorReviews());
        return "comments";
    }
}
