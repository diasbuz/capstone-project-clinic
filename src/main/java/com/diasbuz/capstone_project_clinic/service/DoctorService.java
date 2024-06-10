package com.diasbuz.capstone_project_clinic.service;

import com.diasbuz.capstone_project_clinic.model.Appointment;
import com.diasbuz.capstone_project_clinic.model.Doctor;
import com.diasbuz.capstone_project_clinic.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserService userService;

    public List<Appointment> findAppointments() {
        Doctor doctor = userService.getCurrentDoctor();
        return appointmentRepository.findByDoctor(doctor);
    }

    public void saveAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }
}