package com.tms.springsecurity.service.impl;

import com.tms.springsecurity.model.Speciality;
import com.tms.springsecurity.repository.SpecialityRepository;
import com.tms.springsecurity.service.SpecialityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SpecialityServiceImpl implements SpecialityService {
    private final SpecialityRepository repository;
    @Override
    public List<Speciality> getAll() {
        return repository.findAll();
    }
}
