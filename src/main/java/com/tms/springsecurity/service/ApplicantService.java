package com.tms.springsecurity.service;

import com.tms.springsecurity.dto.ApplicantDto;
import com.tms.springsecurity.model.Applicant;

import java.util.List;

public interface ApplicantService {
    ApplicantDto findById(Integer id);


    ApplicantDto save(ApplicantDto applicantDto);
    List<Applicant> getAll();
    void update(ApplicantDto dto);
    Applicant getCurrentAppl();
    void delete();
}
