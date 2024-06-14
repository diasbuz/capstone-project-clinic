package com.diasbuz.capstone_project_clinic.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clinic_reviews")
@Data
@NoArgsConstructor
public class ClinicReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clinic_review_id")
    private Integer clinicReviewID;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private User patient;

    @Column(name = "review_descr")
    private String reviewDescr;

    @Column(name = "review_title")
    private String reviewTitle;
}