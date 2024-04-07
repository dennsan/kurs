package com.tms.springsecurity.service;

import com.tms.springsecurity.dto.EmployerDto;
import com.tms.springsecurity.model.Applicant;
import com.tms.springsecurity.model.Employer;

import java.util.List;

public interface EmployerService {
    Employer save(Employer employer);
    List<Employer> getAll();
    void update(EmployerDto dto);

    void addApplicant(Integer id);
    List<Applicant> getApplicant();
    void deleteApplicant(Integer id);
    Employer getCurrentEmpl();
    void delete();
}
