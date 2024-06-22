package com.diasbuz.capstone_project_clinic.service;

import com.diasbuz.capstone_project_clinic.model.User;
import com.diasbuz.capstone_project_clinic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findById(Integer doctorId) {
        return userRepository.findById(doctorId).orElse(null);
    }

    @Transactional
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Transactional
    public void updateUserInformation(Integer userId, String name, String phone, String email) {
        userRepository.updateUserInfoById(userId, name, phone, email);
    }

    @Transactional(readOnly = true)
    public List<User> findByServiceServiceIdAndNotNull(Integer serviceId) {
        return userRepository.findByServiceServiceIdAndNotNull(serviceId);
    }

//    @Transactional(readOnly = true)
//    public List<User> findDoctors(String name, String specialization, Double rating) {
//        if (name == null && specialization == null && rating == null) {
//            return userRepository.findAllDoctors();
//        } else {
//            return userRepository.findFilteredDoctors(name, specialization, rating);
//        }
//    }

    @Transactional(readOnly = true)
    public List<User> findAllDoctors() {
        return userRepository.findAllDoctors();
    }

    @Transactional
    public void topUpBalance(Integer userId, BigDecimal amount) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setBalance(user.getBalance().add(amount));
        userRepository.save(user);
    }

    @Transactional
    public void deductBalance(Integer userId, BigDecimal amount) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (user.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        user.setBalance(user.getBalance().subtract(amount));
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findUserByEmail(String email) {
        List<User> users = userRepository.findByEmail(email);
        if (users.isEmpty()) {
            return null;
        } else {
            return users.getFirst();
        }
    }
}