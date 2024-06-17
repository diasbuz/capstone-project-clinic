package com.diasbuz.capstone_project_clinic.service;

import com.diasbuz.capstone_project_clinic.model.User;
import com.diasbuz.capstone_project_clinic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer doctorId) {
        return userRepository.findById(doctorId).orElse(null);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Transactional
    public void updateUserInformation(Integer userId, String name, String phone, String email) {
        userRepository.updateUserInfoById(userId, name, phone, email);
    }

    public List<User> findDoctorsByServiceId(Integer serviceId) {
        return userRepository.findByServiceServiceId(serviceId);
    }

    public List<User> findDoctors(String name, String specialization, Double rating) {
        if (name == null && specialization == null && rating == null) {
            return userRepository.findAllDoctors();
        } else {
            return userRepository.findFilteredDoctors(name, specialization, rating);
        }
    }

    public List<User> findAllDoctors() {
        return userRepository.findAllDoctors();
    }
}