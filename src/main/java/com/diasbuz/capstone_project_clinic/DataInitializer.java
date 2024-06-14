package com.diasbuz.capstone_project_clinic;

import com.diasbuz.capstone_project_clinic.model.Role;
import com.diasbuz.capstone_project_clinic.model.Roles;
import com.diasbuz.capstone_project_clinic.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Bean
    CommandLineRunner init() {
        return args -> {
            if (roleRepository.count() == 0) {
                Role adminRole = new Role();
                adminRole.setRoleName("ADMIN");
                roleRepository.save(adminRole);

                Role patientRole = new Role();
                patientRole.setRoleName("PATIENT");
                roleRepository.save(patientRole);

                Role doctorRole = new Role();
                doctorRole.setRoleName("DOCTOR");
                roleRepository.save(doctorRole);
            }
        };
    }
}
