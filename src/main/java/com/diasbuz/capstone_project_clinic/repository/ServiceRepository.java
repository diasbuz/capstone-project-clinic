package com.diasbuz.capstone_project_clinic.repository;

import com.diasbuz.capstone_project_clinic.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
    @Query("SELECT s, COUNT(u) as numberOfDoctors FROM Service s LEFT JOIN User u ON s.serviceId = u.service.serviceId GROUP BY s")
    List<Object[]> findAllServicesWithDoctorCount();
}
