package com.tms.springsecurity.service;

import com.tms.springsecurity.dto.ApplicantDto;
import com.tms.springsecurity.dto.EmployerDto;
import com.tms.springsecurity.dto.UserDto;
import com.tms.springsecurity.model.Status;

import java.util.List;

public interface AdminService {
    List<EmployerDto> getEmployers();
    List<ApplicantDto> getApplicants();
       void deleteEmployer(Integer id);
    void deleteApplicant(Integer id);
    void save(UserDto dto);


}
