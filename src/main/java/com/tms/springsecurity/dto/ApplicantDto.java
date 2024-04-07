package com.tms.springsecurity.dto;

import com.tms.springsecurity.model.Employer;
import com.tms.springsecurity.model.Language;
import com.tms.springsecurity.model.Speciality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicantDto {
    private Integer id;
    private String name;
    private String surname;
    private Boolean vca;
    private Employer employer;
    private Language language;
    private Speciality speciality;
    private List<Language> languages;
    private List<Speciality> specialities;


}
