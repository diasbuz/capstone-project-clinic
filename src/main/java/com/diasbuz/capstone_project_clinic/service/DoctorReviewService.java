package com.diasbuz.capstone_project_clinic.service;

import com.diasbuz.capstone_project_clinic.model.DoctorReview;
import com.diasbuz.capstone_project_clinic.model.User;
import com.diasbuz.capstone_project_clinic.repository.DoctorReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorReviewService {

    @Autowired
    private DoctorReviewRepository doctorReviewRepository;

    @Transactional(readOnly = true)
    public List<DoctorReview> findByDoctor(User doctor) {
        return doctorReviewRepository.findByDoctor(doctor);
    }

    @Transactional
    public void save(DoctorReview doctorReview) {
        doctorReviewRepository.save(doctorReview);
    }
}
