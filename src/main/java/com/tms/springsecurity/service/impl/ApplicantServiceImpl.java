package com.tms.springsecurity.service.impl;

import com.tms.springsecurity.dto.ApplicantDto;
import com.tms.springsecurity.mapper.ApplicantMapper;
import com.tms.springsecurity.model.Applicant;
import com.tms.springsecurity.model.User;
import com.tms.springsecurity.repository.ApplicantRepository;
import com.tms.springsecurity.service.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ApplicantServiceImpl implements ApplicantService {
    private final ApplicantRepository repository;
    private final ApplicantMapper mapper;

    @Override
    @Transactional
    public ApplicantDto save(ApplicantDto applicantDto) {
        return mapper.toDto(repository.save(mapper.toEntity(applicantDto)));
    }

    @Override
    public ApplicantDto findById(Integer id) {
        var applicant = repository.findById(id).orElseThrow(() -> new RuntimeException());
        return mapper.toDto(applicant);
    }

    @Override
    public List<Applicant> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void update(ApplicantDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        User user = (User) principal;
        Applicant applicant1 = user.getApplicant();
        Integer id = applicant1.getId();
        Optional<Applicant> applicantId = repository.findById(id);
        Applicant applicant = applicantId.get();
        applicant.setName(dto.getName());
        applicant.setSurname(dto.getSurname());
        applicant.setVca(dto.getVca());
        if (applicant.getLanguages().contains(dto.getLanguage())){

        }else {
            applicant.addLang(dto.getLanguage());
        }
        if (applicant.getSpecialities().contains(dto.getSpeciality())){

        }else {
            applicant.addSpec(dto.getSpeciality());
        }
    }
}
