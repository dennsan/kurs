package com.tms.springsecurity.service.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.tms.springsecurity.dto.ApplicantDto;
import com.tms.springsecurity.dto.EmployerDto;
import com.tms.springsecurity.dto.UserDto;
import com.tms.springsecurity.mapper.ApplicantMapper;
import com.tms.springsecurity.mapper.EmployerMapper;
import com.tms.springsecurity.model.Employer;
import com.tms.springsecurity.model.Role;
import com.tms.springsecurity.model.User;
import com.tms.springsecurity.repository.UserRepository;
import com.tms.springsecurity.service.ApplicantService;
import com.tms.springsecurity.service.DbUserDetailService;
import com.tms.springsecurity.service.EmployerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {
    @InjectMocks
    AdminServiceImpl adminService;
    @Mock
    EmployerService employerService;
    @Mock
    EmployerMapper employerMapper;
    @Mock
    ApplicantService applicantService;
    @Mock
    ApplicantMapper applicantMapper;
    @Mock
    UserRepository userRepository;
    @Mock
    DbUserDetailService userDetailService;
    @Test
    void getEmployers() {
        Mockito.when(adminService.getEmployers()).thenReturn(List.of(new EmployerDto(), new EmployerDto(),new EmployerDto()));
        List<EmployerDto> employerDtos = employerMapper.toDtos(employerService.getAll());
        int size = employerDtos.size();
        Assertions.assertEquals(3, size);
    }

    @Test
    void getEmployers_times_run() {
        Mockito.when(adminService.getEmployers()).thenReturn(List.of(new EmployerDto(), new EmployerDto(),new EmployerDto()));
        employerMapper.toDtos(employerService.getAll());
        verify(employerService, times(2)).getAll();
    }

    @Test
    void getApplicants() {
        Mockito.when(adminService.getApplicants()).thenReturn(List.of(new ApplicantDto(), new ApplicantDto(), new ApplicantDto()));
        List<ApplicantDto> applicantDtoList = applicantMapper.toDtos(applicantService.getAll());
        int size = applicantDtoList.size();
        Assertions.assertEquals(3, size);
    }
    @Test
    void getApplicants_times_run() {
        Mockito.when(adminService.getApplicants()).thenReturn(List.of(new ApplicantDto(), new ApplicantDto(), new ApplicantDto()));
        applicantMapper.toDtos(applicantService.getAll());
        verify(applicantService, times(2)).getAll();
    }

    @Test
    void save() {
       UserDto user = new UserDto("user1" ,"pass1", Role.ADMIN);
       adminService.save(user);
        ArgumentCaptor<UserDto> captor = ArgumentCaptor.forClass(UserDto.class);
        verify(userDetailService).save(captor.capture());
        assertEquals("user1", captor.getValue().getLogin());

    }
}