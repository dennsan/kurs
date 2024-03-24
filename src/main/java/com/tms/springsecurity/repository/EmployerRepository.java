package com.tms.springsecurity.repository;

import com.tms.springsecurity.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepository extends JpaRepository<Employer, Integer> {
}
