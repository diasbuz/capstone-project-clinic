package com.diasbuz.capstone_project_clinic.controller;

import com.diasbuz.capstone_project_clinic.model.Appointment;
import com.diasbuz.capstone_project_clinic.model.User;
import com.diasbuz.capstone_project_clinic.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/doctor/appointments")
    public String showDoctorAppointments(Model model, @AuthenticationPrincipal User doctor) {
        List<Appointment> appointments = appointmentService.findByDoctor(doctor);
        model.addAttribute("appointments", appointments);
        return "appointments";
    }

    @GetMapping("/patient/appointments")
    public String showPatientAppointments(Model model, @AuthenticationPrincipal User patient) {
        List<Appointment> appointments = appointmentService.findByPatient(patient);
        model.addAttribute("appointments", appointments);
        return "appointments";
    }

    @PostMapping("/create-appointment")
    public String createAppointment(@ModelAttribute Appointment appointment) {
        appointmentService.save(appointment);
        return "redirect:/doctor/appointments";
    }
}
