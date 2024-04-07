package com.tms.springsecurity.dto;

import com.tms.springsecurity.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDto {
    private String login;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
