package com.diasbuz.capstone_project_clinic.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Patient extends User {
    private Integer balance;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "patient")
    private List<DoctorReview> reviews;

    @OneToMany(mappedBy = "patient")
    private List<ClinicReview> clinicReviews;
}