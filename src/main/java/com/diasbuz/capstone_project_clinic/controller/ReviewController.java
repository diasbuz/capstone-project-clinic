package com.diasbuz.capstone_project_clinic.controller;

import com.diasbuz.capstone_project_clinic.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public String reviews(Model model) {
        model.addAttribute("reviews", reviewService.findAllReviews());
        return "reviews";
    }
}