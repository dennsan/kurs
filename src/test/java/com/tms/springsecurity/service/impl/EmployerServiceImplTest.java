package com.tms.springsecurity.service.impl;

import com.tms.springsecurity.model.Applicant;
import com.tms.springsecurity.model.Employer;
import com.tms.springsecurity.repository.ApplicantRepository;
import com.tms.springsecurity.repository.EmployerRepository;
import com.tms.springsecurity.service.ApplicantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@ExtendWith(MockitoExtension.class)
class EmployerServiceImplTest {
    @InjectMocks
    EmployerServiceImpl employerService;
    @Mock
    EmployerRepository employerRepository;
    @Mock
    ApplicantRepository applicantRepository;
    @Mock
    ApplicantService applicantService;
@Mock
    Employer employer;
@Mock
    Applicant applicant;
@Mock
    Applicant applicant1;
   @BeforeEach
    void setUp(){
        employer = Employer.builder()
                .id(1)
                .name("name")
                .phone("283749983")
                .BTV("234567876")
                .city("city")
                .contactName(null)
                .applicants(List.of(this.applicant,this.applicant1))
                .build();
        applicant = Applicant.builder()
                .id(1)
                .name("user")
                .surname("name")
                .vca(false)
                .languages(null)
                .specialities(null)
                .employer(employer)
                .build();
        applicant1 = Applicant.builder()
                .id(2)
                .name("user2")
                .surname("name2")
                .vca(false)
                .languages(null)
                .specialities(null)
                .employer(employer)
                .build();
    }

    @Test
    void test_save() {
        lenient().when(employerService.save(employer)).thenReturn(employer);
        assertEquals("name", employer.getName());
    }

    @Test
    void test_getAll() {
        when(employerService.getAll()).thenReturn(List.of(new Employer(), new Employer()));
        List<Employer> employers = employerRepository.findAll();
        int size = employers.size();
        assertEquals(2,size);
    }
    @Test
    void test_deleteApplicant() {
        when(applicantService.getAll()).thenReturn(List.of(this.applicant1,this.applicant));
        employer.setApplicants(List.of(this.applicant,this.applicant1));
        for (Applicant worker : employer.getApplicants()) {
            employerService.deleteApplicant(worker.getId());
        }
        assertNull(applicant.getEmployer());
        assertNull(applicant1.getEmployer());
    }

}