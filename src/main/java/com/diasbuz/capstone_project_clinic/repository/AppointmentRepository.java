package com.diasbuz.capstone_project_clinic.repository;

import com.diasbuz.capstone_project_clinic.model.Appointment;
import com.diasbuz.capstone_project_clinic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByDoctor(User doctor);
    List<Appointment> findByPatient(User patient);
}