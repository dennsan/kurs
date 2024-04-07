package com.tms.springsecurity.service.impl;

import com.tms.springsecurity.dto.ApplicantDto;
import com.tms.springsecurity.mapper.ApplicantMapperImpl;
import com.tms.springsecurity.model.*;
import com.tms.springsecurity.repository.ApplicantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicantServiceImplTest {
    @InjectMocks
    ApplicantServiceImpl applicantService;
    @Mock
    ApplicantRepository applicantRepository;
    @Mock
    ApplicantMapperImpl applicantMapper;

    @Test
    void save() {
        ApplicantDto applicant = new ApplicantDto(1,"user","name", false, null,null,null,null,null);
        Mockito.lenient().when(applicantService.save(applicant)).thenReturn(applicant);
        Assertions.assertEquals("user", applicant.getName());
    }

    @Test
    void test_findById_good() {
        Applicant applicant = Applicant.builder()
                .id(2)
                .name("user")
                .surname("surname")
                .build();
        lenient().when(applicantRepository.findById(applicant.getId())).thenReturn(Optional.of(applicant));
        ApplicantDto applicantDto = ApplicantDto.builder()
                .id(2)
                .name("user")
                .surname("name")
                .build();
        applicantService.findById(applicantDto.getId());
        assertEquals(applicant.getName(),applicantDto.getName());

    }
    @Test
    void test_findById_throw_exception() {
        ApplicantDto applicantDto = ApplicantDto.builder()
                .id(2)
                .name("user")
                .surname("name")
                .build();
        when(applicantRepository.findById(1));
assertThrows(RuntimeException.class, () -> applicantService.findById(applicantDto.getId()));

    }

    @Test
    void getAll() {
        when(applicantService.getAll()).thenReturn(List.of(new Applicant(), new Applicant(),new Applicant()));
        List<Applicant> applicantList = applicantRepository.findAll();
        int size = applicantList.size();
        assertEquals(3,size);
    }

}