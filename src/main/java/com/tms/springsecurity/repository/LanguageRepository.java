package com.tms.springsecurity.repository;

import com.tms.springsecurity.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
}
