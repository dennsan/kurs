package com.tms.springsecurity.service;

import com.tms.springsecurity.dto.UserDto;
import com.tms.springsecurity.model.Role;
import com.tms.springsecurity.model.User;
import com.tms.springsecurity.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DbUserDetailServiceTest {
    @InjectMocks
    DbUserDetailService userDetailService;
    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;


    @Test
    void loadUserByUsername() {
        User user = User.builder()
                .id(1)
                .login("user")
                .password("pass")
                .build();
        when(userRepository.findByLogin(user.getLogin())).thenReturn(Optional.of(user));
        userDetailService.loadUserByUsername(user.getLogin());
        assertEquals("user", user.getLogin());
    }

    @Test
    void save() {
        UserDto userDto = UserDto.builder()
                .login("user")
                .password("pass")
                .role(Role.ADMIN)
                .build();
        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setPerm("ROLE_" + userDto.getRole().name());
        when(passwordEncoder.encode(user.getPassword())).thenReturn("pass");
        when(userRepository.save(user)).thenReturn(user);
        userDetailService.save(userDto);
        assertEquals("user", user.getLogin());
    }
}