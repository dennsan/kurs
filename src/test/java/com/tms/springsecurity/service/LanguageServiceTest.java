package com.tms.springsecurity.service;

import com.tms.springsecurity.model.Language;
import com.tms.springsecurity.repository.LanguageRepository;
import com.tms.springsecurity.service.impl.LanguageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LanguageServiceTest {
    @InjectMocks
    LanguageServiceImpl languageService;
    @Mock
    LanguageRepository languageRepository;

    @Test
    void test_findAll() {
       when(languageRepository.findAll()).thenReturn(List.of(new Language(), new Language()));
        List<Language> languages = languageService.findAll();
        int size = languages.size();
        assertEquals(2, size);
    }
}