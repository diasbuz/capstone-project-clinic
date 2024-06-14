package com.diasbuz.capstone_project_clinic.service;

import com.diasbuz.capstone_project_clinic.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class serviceService {

    @Autowired
    private ServiceRepository serviceRepository;


    public Map<com.diasbuz.capstone_project_clinic.model.Service, Long> getAllServicesWithDoctorCount() {
        List<Object[]> results = serviceRepository.findAllServicesWithDoctorCount();
        Map<com.diasbuz.capstone_project_clinic.model.Service, Long> servicesWithDoctorCount = new HashMap<>();
        for (Object[] result : results) {
            com.diasbuz.capstone_project_clinic.model.Service service = (com.diasbuz.capstone_project_clinic.model.Service) result[0];
            Long doctorCount = (Long) result[1];
            servicesWithDoctorCount.put(service, doctorCount);
        }
        return servicesWithDoctorCount;
    }
}
