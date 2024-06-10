package com.diasbuz.capstone_project_clinic.service;

import com.diasbuz.capstone_project_clinic.model.ClinicReview;
import com.diasbuz.capstone_project_clinic.repository.ClinicReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ClinicReviewRepository clinicReviewRepository;

    public List<ClinicReview> findAllReviews() {
        return clinicReviewRepository.findAll();
    }
}
