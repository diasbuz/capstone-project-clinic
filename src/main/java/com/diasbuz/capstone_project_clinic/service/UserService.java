package com.diasbuz.capstone_project_clinic.service;

import com.diasbuz.capstone_project_clinic.model.Doctor;
import com.diasbuz.capstone_project_clinic.model.Patient;
import com.diasbuz.capstone_project_clinic.model.User;
import com.diasbuz.capstone_project_clinic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Doctor getCurrentDoctor() {
        User currentUser = getCurrentUser();
        if (currentUser instanceof Doctor) {
            return (Doctor) currentUser;
        } else {
            throw new IllegalStateException("Current user is not a doctor");
        }
    }

    public Patient getCurrentPatient() {
        User currentUser = getCurrentUser();
        if (currentUser instanceof Patient) {
            return (Patient) currentUser;
        } else {
            throw new IllegalStateException("Current user is not a patient");
        }
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
