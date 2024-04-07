package com.tms.springsecurity.service.impl;

import com.tms.springsecurity.model.Speciality;
import com.tms.springsecurity.repository.SpecialityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SpecialityServiceImplTest {
@InjectMocks
SpecialityServiceImpl specialityService;
@Mock
    SpecialityRepository specialityRepository;
    @Test
    void test_getAll() {
        when(specialityRepository.findAll()).thenReturn(List.of(new Speciality(), new Speciality()));
        int size = specialityService.getAll().size();
        assertEquals(2, size);

    }
}