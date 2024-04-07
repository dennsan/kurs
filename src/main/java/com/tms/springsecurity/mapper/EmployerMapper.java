package com.tms.springsecurity.mapper;

import com.tms.springsecurity.dto.EmployerDto;
import com.tms.springsecurity.model.Employer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployerMapper {
    Employer toEntity(EmployerDto dto);
    EmployerDto toDto(Employer employer);
    List<EmployerDto> toDtos(List<Employer> employers);
}
