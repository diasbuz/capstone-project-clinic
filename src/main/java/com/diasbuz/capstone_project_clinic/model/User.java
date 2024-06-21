package com.diasbuz.capstone_project_clinic.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Roles role;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @OneToMany(mappedBy = "doctor")
    private List<DoctorReview> doctorReviews;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> doctorAppointments;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> patientAppointments;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "balance", precision = 15, scale = 2)
    private BigDecimal balance;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(role);
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", role=" + role +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", rating=" + rating +
                '}';
    }

    public Double getAverageRating()
    {
        if(doctorReviews == null || doctorReviews.isEmpty())
        {
            return 0.0;
        }
        return doctorReviews.stream().mapToDouble(DoctorReview::getRating).sum();
    }
}