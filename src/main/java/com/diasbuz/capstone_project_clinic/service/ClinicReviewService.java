package com.diasbuz.capstone_project_clinic.service;

import com.diasbuz.capstone_project_clinic.model.ClinicReview;
import com.diasbuz.capstone_project_clinic.model.User;
import com.diasbuz.capstone_project_clinic.repository.ClinicReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicReviewService {

    @Autowired
    private ClinicReviewRepository clinicReviewRepository;

    public List<ClinicReview> findByPatient(User patient) {
        return clinicReviewRepository.findByPatient(patient);
    }

    public void save(ClinicReview clinicReview) {
        clinicReviewRepository.save(clinicReview);
    }

    public List<ClinicReview> findAll() {
        return clinicReviewRepository.findAll();
    }
}
