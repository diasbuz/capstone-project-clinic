package com.diasbuz.capstone_project_clinic.service;

import com.diasbuz.capstone_project_clinic.model.DoctorReview;
import com.diasbuz.capstone_project_clinic.model.User;
import com.diasbuz.capstone_project_clinic.repository.DoctorReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorReviewService {

    @Autowired
    private DoctorReviewRepository doctorReviewRepository;

    public List<DoctorReview> findByDoctor(User doctor) {
        return doctorReviewRepository.findByDoctor(doctor);
    }

    public void save(DoctorReview doctorReview) {
        doctorReviewRepository.save(doctorReview);
    }
}
