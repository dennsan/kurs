package com.tms.springsecurity.dto;

import com.tms.springsecurity.model.Applicant;
import com.tms.springsecurity.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerDto {
    private Integer id;
    private String name;
    private String BTV;
    private String phone;
    private String city;
    private String contactName;
    private List<Applicant> applicants;
    private User user;
}
