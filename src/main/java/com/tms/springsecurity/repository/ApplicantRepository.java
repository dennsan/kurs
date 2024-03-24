package com.tms.springsecurity.repository;

import com.tms.springsecurity.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
}
