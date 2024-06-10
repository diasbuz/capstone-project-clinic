package com.diasbuz.capstone_project_clinic.repository;

import com.diasbuz.capstone_project_clinic.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {}
