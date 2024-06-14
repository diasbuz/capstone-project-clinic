package com.diasbuz.capstone_project_clinic.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum Roles implements GrantedAuthority {
    ROLE_ADMIN("ADMIN"), ROLE_PATIENT("PATIENT"), ROLE_DOCTOR("DOCTOR");

    private final String role;

    Roles(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return this.name();
    }
}
