package com.diasbuz.capstone_project_clinic.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ClinicReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clinicReviewID;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String reviewDescr;
    private String reviewTitle;
}