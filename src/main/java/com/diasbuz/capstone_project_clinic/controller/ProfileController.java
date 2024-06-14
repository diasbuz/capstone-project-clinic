package com.diasbuz.capstone_project_clinic.controller;

import com.diasbuz.capstone_project_clinic.model.Appointment;
import com.diasbuz.capstone_project_clinic.model.Roles;
import com.diasbuz.capstone_project_clinic.model.Service;
import com.diasbuz.capstone_project_clinic.model.User;
import com.diasbuz.capstone_project_clinic.repository.ServiceRepository;
import com.diasbuz.capstone_project_clinic.service.AppointmentService;
import com.diasbuz.capstone_project_clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping
    public String showProfile(@AuthenticationPrincipal User user, Model model) {
        String role = user.getAuthorities().iterator().next().getAuthority();
        model.addAttribute("user", user);
        model.addAttribute("role", role);
        model.addAttribute("services", serviceRepository.findAll());
        if(user.getRole() == Roles.ROLE_PATIENT) {
            model.addAttribute("appointments", appointmentService.findByPatient(user));
        }
        return "profile";
    }

    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute User updatedUser, @AuthenticationPrincipal User currentUser) {
        if (!currentUser.getUserId().equals(updatedUser.getUserId())) {
            throw new IllegalArgumentException("Unauthorized update");
        }

        userService.updateUserInformation(
                updatedUser.getUserId(),
                updatedUser.getName(),
                updatedUser.getPhone(),
                updatedUser.getEmail()
        );

        return "redirect:/profile";
    }


    @PostMapping("/add-doctor")
    public String addDoctor(@ModelAttribute("user") User user, BindingResult result, Model model) {
        model.addAttribute("services", serviceRepository.findAll());

        User existingUser = userService.findByLogin(user.getLogin());
        if (existingUser != null) {
            result.rejectValue("login", null, "There is already an account registered with this login");
        }

        if (user.getService() == null || user.getService().getServiceId() == null) {
            result.rejectValue("service.id", null, "Please select a service");
        } else {
            Service selectedService = serviceRepository.findById(user.getService().getServiceId()).orElse(null);
            if (selectedService == null) {
                result.rejectValue("service.id", null, "Selected service does not exist");
            } else {
                user.setService(selectedService);
            }
        }

        if (result.hasErrors()) {
            return "add-doctor";
        }

        user.setRole(Roles.ROLE_DOCTOR);

        userService.saveUser(user);

        return "redirect:/profile";
    }

    @GetMapping("/doctor/appointments")
    public String showDoctorAppointments(@AuthenticationPrincipal User doctor, Model model) {
        if (!doctor.getRole().equals(Roles.ROLE_DOCTOR)) {
            return "redirect:/profile?error=Unauthorized";
        }
        List<Appointment> appointments = appointmentService.findByDoctor(doctor);
        model.addAttribute("appointments", appointments);
        return "appointments";
    }

    @PostMapping("/create-appointment")
    public String createAppointment(@ModelAttribute Appointment appointment, @AuthenticationPrincipal User doctor) {
        if (!doctor.getRole().equals(Roles.ROLE_DOCTOR)) {
            return "redirect:/profile?error=Unauthorized";
        }
        appointment.setDoctor(doctor);
        appointmentService.save(appointment);
        return "redirect:/profile/doctor/appointments";
    }

    @GetMapping("/patient/appointments")
    public String showPatientAppointments(@AuthenticationPrincipal User patient, Model model) {
        if (!patient.getRole().equals(Roles.ROLE_PATIENT)) {
            return "redirect:/profile?error=Unauthorized";
        }
        List<Appointment> appointments = appointmentService.findByPatient(patient);
        model.addAttribute("appointments", appointments);
        return "appointments";
    }
}