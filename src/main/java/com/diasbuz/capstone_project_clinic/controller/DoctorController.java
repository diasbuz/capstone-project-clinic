package com.diasbuz.capstone_project_clinic.controller;

import com.diasbuz.capstone_project_clinic.model.Appointment;
import com.diasbuz.capstone_project_clinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/appointments")
    public String appointments(Model model) {
        model.addAttribute("appointments", doctorService.findAppointments());
        return "doctor/appointments";
    }

    @PostMapping("/appointments/new")
    public String createAppointment(Appointment appointment) {
        doctorService.saveAppointment(appointment);
        return "redirect:/doctor/appointments";
    }
}

