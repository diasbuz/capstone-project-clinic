package com.diasbuz.capstone_project_clinic.controller;

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

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/services")
public class ServicesController {

    @Autowired
    private serviceService ServiceService;

    @Autowired
    private UserService userService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public String showServices(Model model) {
        Map<Service, Long> servicesWithDoctorCount = ServiceService.getAllServicesWithDoctorCount();
        model.addAttribute("servicesWithDoctorCount", servicesWithDoctorCount);
        return "services";
    }
    @GetMapping("/doctors/{serviceId}")
    public String viewDoctors(@PathVariable Integer serviceId, Model model) {
        List<User> doctors = userService.findDoctorsByServiceId(serviceId);
        model.addAttribute("doctors", doctors);
        return "doctors";
    }

    @GetMapping("/{doctorId}/booking")
    public String bookAppointment(@PathVariable Integer doctorId, Model model) {
        User doctor = userService.findById(doctorId);
        model.addAttribute("doctor", doctor);
        return "booking";
    }

    @PostMapping("/confirm-booking")
    public String confirmBooking(@RequestParam Integer doctorId,
                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date appointmentDateTime,
                                 Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User patient = (User) authentication.getPrincipal();


        appointmentService.bookAppointment(doctorId, patient.getUserId(), appointmentDateTime);


        model.addAttribute("message", "Appointment successfully booked!");

        return "booking-confirmation";
    }
}
