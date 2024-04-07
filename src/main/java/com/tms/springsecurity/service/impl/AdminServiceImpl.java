package com.tms.springsecurity.service.impl;

import com.tms.springsecurity.dto.ApplicantDto;
import com.tms.springsecurity.dto.EmployerDto;
import com.tms.springsecurity.dto.UserDto;
import com.tms.springsecurity.mapper.ApplicantMapper;
import com.tms.springsecurity.mapper.EmployerMapper;
import com.tms.springsecurity.model.Applicant;
import com.tms.springsecurity.model.Employer;
import com.tms.springsecurity.model.User;
import com.tms.springsecurity.repository.ApplicantRepository;
import com.tms.springsecurity.repository.EmployerRepository;
import com.tms.springsecurity.repository.UserRepository;
import com.tms.springsecurity.service.AdminService;
import com.tms.springsecurity.service.ApplicantService;
import com.tms.springsecurity.service.DbUserDetailService;
import com.tms.springsecurity.service.EmployerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {
    private final ApplicantService applicantService;
    private final EmployerService employerService;
    private final ApplicantMapper applicantMapper;
    private final EmployerMapper employerMapper;
    private final DbUserDetailService userDetailService;
    private final UserRepository userRepository;
    private final EmployerRepository employerRepository;
    private final ApplicantRepository applicantRepository;
    @Override
    public List<EmployerDto> getEmployers() {
        return employerMapper.toDtos(employerService.getAll());
    }

    @Override
    public List<ApplicantDto> getApplicants() {
        return applicantMapper.toDtos(applicantService.getAll());
    }

    @Override
    public void deleteEmployer(Integer id) {
       var user = userRepository.findById(id).orElseThrow();
        Employer employer = user.getEmployer();
        for (Applicant applicant : employer.getApplicants()) {
            applicant.setEmployer(null);
        }
        employerRepository.delete(employer);
    }

    @Override
    public void deleteApplicant(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        Applicant applicant = user.getApplicant();
        applicantRepository.delete(applicant);
    }

    @Override
    public void save(UserDto dto) {
        userDetailService.save(dto);
    }
}
