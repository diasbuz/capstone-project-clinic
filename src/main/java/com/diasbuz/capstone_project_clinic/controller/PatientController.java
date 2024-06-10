package com.diasbuz.capstone_project_clinic.controller;

import com.diasbuz.capstone_project_clinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/patient/appointments")
    public String viewAppointments(Model model) {
        model.addAttribute("appointments", patientService.findAppointments());
        return "patient-appointments";
    }
}