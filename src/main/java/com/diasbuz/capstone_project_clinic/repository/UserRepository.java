package com.diasbuz.capstone_project_clinic.repository;

import com.diasbuz.capstone_project_clinic.model.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.name = :name, u.phone = :phone, u.email = :email WHERE u.userId = :userId")
    void updateUserInfoById(Integer userId, String name, String phone, String email);

    List<User> findByServiceServiceId(Integer serviceId);
}