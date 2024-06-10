package com.diasbuz.capstone_project_clinic.service;

import com.diasbuz.capstone_project_clinic.model.Doctor;
import com.diasbuz.capstone_project_clinic.model.User;
import com.diasbuz.capstone_project_clinic.repository.DoctorRepository;
import com.diasbuz.capstone_project_clinic.repository.ServiceRepository;
import com.diasbuz.capstone_project_clinic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    public List<com.diasbuz.capstone_project_clinic.model.Service> findAllServices() {
        return serviceRepository.findAll();
    }

    public void saveDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }
}
