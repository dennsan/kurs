package com.tms.springsecurity.repository;

import com.tms.springsecurity.model.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialityRepository extends JpaRepository<Speciality, Integer> {
}
