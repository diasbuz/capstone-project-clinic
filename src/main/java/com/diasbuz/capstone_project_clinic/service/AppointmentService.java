package com.diasbuz.capstone_project_clinic.service;

import com.diasbuz.capstone_project_clinic.model.Appointment;
import com.diasbuz.capstone_project_clinic.model.User;
import com.diasbuz.capstone_project_clinic.repository.AppointmentRepository;
import com.diasbuz.capstone_project_clinic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Transactional(readOnly = true)
    public List<Appointment> findByDoctor(User doctor) {
        return appointmentRepository.findByDoctor(doctor);
    }

    @Transactional(readOnly = true)
    public List<Appointment> findByPatient(User patient) {
        return appointmentRepository.findByPatient(patient);
    }

    @Transactional
    public void save(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    @Transactional
    public void bookAppointment(Integer appointmentId, Integer patientId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid appointment ID"));

        if ("available".equals(appointment.getStatus())) {
            User patient = userRepository.findById(patientId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid patient ID"));
            userService.deductBalance(patientId, new BigDecimal(50));

            appointment.setStatus("booked");
            appointment.setPatient(patient);
            appointmentRepository.save(appointment);
        } else {
            throw new IllegalStateException("Appointment is not available");
        }
    }

    public boolean isAppointmentSlotTaken(User doctor, LocalDateTime dateTime) {
        return appointmentRepository.existsByDoctorAndDateTime(doctor, dateTime);
    }

    @Transactional(readOnly = true)
    public List<Appointment> getAvailableAppointments(Integer doctorId) {
        User doctor = userRepository.findById(doctorId).orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID"));

        return appointmentRepository.findByDoctorAndStatus(doctor, "available");
    }
}