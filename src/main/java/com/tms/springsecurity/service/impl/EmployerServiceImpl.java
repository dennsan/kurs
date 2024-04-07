package com.tms.springsecurity.service.impl;


import com.tms.springsecurity.dto.EmployerDto;
import com.tms.springsecurity.model.Applicant;
import com.tms.springsecurity.model.User;
import com.tms.springsecurity.repository.ApplicantRepository;
import com.tms.springsecurity.repository.EmployerRepository;
import com.tms.springsecurity.service.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import com.tms.springsecurity.model.Employer;
import com.tms.springsecurity.service.EmployerService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployerServiceImpl implements EmployerService {
    private final EmployerRepository repository;
    private final ApplicantRepository applicantRepository;
    private final ApplicantService applicantService;

    @Override
    public Employer save(Employer employer) {
        return repository.save(employer);
    }

    @Override
    public List<Employer> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void update(EmployerDto dto) {
        Employer employer = getCurrentEmpl();
        employer.setName(dto.getName());
        employer.setBTV(dto.getBTV());
        employer.setCity(dto.getCity());
        employer.setContactName(dto.getContactName());
        employer.setPhone(dto.getPhone());
    }

    @Override
    @Transactional
    public void addApplicant(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();
        Employer employer = principal.getEmployer();
        var applicant = applicantRepository.findById(id).orElseThrow();
        employer.addApplicant(applicant);
    }

    @Override
    public List<Applicant> getApplicant() {
        List<Applicant> applicants = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();
        Employer employer = principal.getEmployer();
        for (Applicant applicant : applicantService.getAll()) {
            if (applicant.getEmployer() != null) {
                if (applicant.getEmployer().getId().equals(employer.getId())) {
                    applicants.add(applicant);
                }
            }
        }
        return applicants;
    }

    @Override
    @Transactional
    public void deleteApplicant(Integer id) {
        List<Applicant> applicants = applicantService.getAll();
        for (Applicant worker : applicants) {
            if (worker.getId().equals(id)) {
                worker.setEmployer(null);
            }
        }
    }
    @Override
    public Employer getCurrentEmpl(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Employer employer = user.getEmployer();
        Integer id = employer.getId();
        Optional<Employer> employerOptional = repository.findById(id);
        return employerOptional.get();
    }

    @Override
    public void delete() {
        Employer employer = getCurrentEmpl();
        repository.delete(employer);
    }
}
