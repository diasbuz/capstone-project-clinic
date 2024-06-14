package com.diasbuz.capstone_project_clinic.service;

import com.diasbuz.capstone_project_clinic.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public List<com.diasbuz.capstone_project_clinic.model.Service> findAll() {
        return serviceRepository.findAll();
    }

    public void save(com.diasbuz.capstone_project_clinic.model.Service service) {
        serviceRepository.save(service);
    }
}
