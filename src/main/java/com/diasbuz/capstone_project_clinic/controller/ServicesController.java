package com.diasbuz.capstone_project_clinic.controller;

import com.diasbuz.capstone_project_clinic.model.Appointment;
import com.diasbuz.capstone_project_clinic.model.Service;
import com.diasbuz.capstone_project_clinic.model.User;
import com.diasbuz.capstone_project_clinic.service.AppointmentService;
import com.diasbuz.capstone_project_clinic.service.UserService;
import com.diasbuz.capstone_project_clinic.service.serviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/services")
public class ServicesController {

    @Autowired
    private serviceService serviceService;

    @Autowired
    private UserService userService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public String showServices(Model model) {
        Map<Service, Long> servicesWithDoctorCount = serviceService.getAllServicesWithDoctorCount();
        model.addAttribute("servicesWithDoctorCount", servicesWithDoctorCount);
        return "services";
    }

    @GetMapping("/doctors/{serviceId}")
    public String viewDoctors(@PathVariable Integer serviceId, Model model) {
        List<User> doctors = userService.findDoctorsByServiceId(serviceId);
        model.addAttribute("doctors", doctors);
        model.addAttribute("serviceId", serviceId);
        return "doctors";
    }

    @GetMapping("/doctors/{serviceId}/{doctorId}/booking")
    public String bookAppointment(@PathVariable Integer doctorId, @PathVariable Integer serviceId, Model model) {
        User doctor = userService.findById(doctorId);
        List<Appointment> availableAppointments = appointmentService.getAvailableAppointments(doctorId);
        model.addAttribute("doctor", doctor);
        model.addAttribute("serviceId", serviceId);
        model.addAttribute("availableAppointments", availableAppointments);
        return "booking";
    }

    @PostMapping("/confirm-booking")
    public String confirmBooking(@RequestParam Integer appointmentId,
                                 @RequestParam Integer doctorId,
                                 @RequestParam Integer serviceId,
                                 Model model)
        {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User patient = (User) authentication.getPrincipal();

            appointmentService.bookAppointment(appointmentId, patient.getUserId());

            model.addAttribute("message", "Appointment successfully booked!");

            return "confirm-booking";
        }
}
