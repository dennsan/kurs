package com.tms.springsecurity.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
@Getter


public enum Role implements GrantedAuthority {
    EMPLOYER,
    APPLICANT,
    ADMIN;


    @Override
    public String getAuthority() {
        return name();
    }
}
