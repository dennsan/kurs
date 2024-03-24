package com.tms.springsecurity.service;

import com.tms.springsecurity.dto.ApplicantDto;
import com.tms.springsecurity.dto.EmployerDto;
import com.tms.springsecurity.model.Applicant;
import com.tms.springsecurity.model.Employer;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface EmployerService {
    Employer save(Employer employer);
    List<Employer> getAll();
    void update(EmployerDto dto);

    void addApplicant(Integer id);
    List<Applicant> getApplicant();
    void deleteApplicant(Integer id);
}
