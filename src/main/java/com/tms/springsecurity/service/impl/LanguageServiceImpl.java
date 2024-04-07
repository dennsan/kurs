package com.tms.springsecurity.service.impl;

import com.tms.springsecurity.model.Language;
import com.tms.springsecurity.repository.LanguageRepository;
import com.tms.springsecurity.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository repository;
    @Override
    public List<Language> findAll() {
        return repository.findAll();
    }
}
