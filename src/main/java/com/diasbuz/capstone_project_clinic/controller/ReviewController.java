package com.diasbuz.capstone_project_clinic.controller;

import com.diasbuz.capstone_project_clinic.model.ClinicReview;
import com.diasbuz.capstone_project_clinic.model.DoctorReview;
import com.diasbuz.capstone_project_clinic.model.User;
import com.diasbuz.capstone_project_clinic.service.ClinicReviewService;
import com.diasbuz.capstone_project_clinic.service.DoctorReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ClinicReviewService clinicReviewService;

    @Autowired
    private DoctorReviewService doctorReviewService;

    @GetMapping
    public String showReviews(@AuthenticationPrincipal User user, Model model) {
        if (user != null) {
            model.addAttribute("role", user.getAuthorities().iterator().next().getAuthority());
        } else {
            model.addAttribute("role", null);
        }
        List<ClinicReview> clinicReviews = clinicReviewService.findAll();
        model.addAttribute("reviews", clinicReviews);
        return "reviews";
    }

    @PostMapping("/add-clinic-review")
    public String addClinicReview(@ModelAttribute ClinicReview clinicReview, @AuthenticationPrincipal User patient) {
        clinicReview.setPatient(patient);
        clinicReviewService.save(clinicReview);
        return "redirect:/reviews";
    }

    @PostMapping("/add-doctor-review")
    public String addDoctorReview(@ModelAttribute DoctorReview doctorReview, @AuthenticationPrincipal User patient) {
        doctorReview.setPatient(patient);
        doctorReviewService.save(doctorReview);
        return "redirect:/reviews";
    }
}