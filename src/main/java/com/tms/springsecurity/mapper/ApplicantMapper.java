package com.tms.springsecurity.mapper;

import com.tms.springsecurity.dto.ApplicantDto;
import com.tms.springsecurity.model.Applicant;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface ApplicantMapper {

    Applicant toEntity (ApplicantDto dto);

    ApplicantDto toDto(Applicant applicant);
    List<ApplicantDto> toDtos(List<Applicant> applicants);

}
