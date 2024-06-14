package com.diasbuz.capstone_project_clinic.repository;

import com.diasbuz.capstone_project_clinic.model.ClinicReview;
import com.diasbuz.capstone_project_clinic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicReviewRepository extends JpaRepository<ClinicReview, Integer> {
    List<ClinicReview> findByPatient(User patient);
}
