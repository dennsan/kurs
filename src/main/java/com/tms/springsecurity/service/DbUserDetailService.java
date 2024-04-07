package com.tms.springsecurity.service;

import com.tms.springsecurity.dto.UserDto;
import com.tms.springsecurity.model.Applicant;
import com.tms.springsecurity.model.Employer;
import com.tms.springsecurity.model.User;
import com.tms.springsecurity.model.Role;
import com.tms.springsecurity.repository.ApplicantRepository;
import com.tms.springsecurity.repository.EmployerRepository;
import com.tms.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class DbUserDetailService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final ApplicantRepository applicantRepository;
    private final EmployerRepository employerRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username)
                .orElse(null);
    }

    @Transactional
    public void save(UserDto user) {
        String username = user.getLogin();
        String password = user.getPassword();
        Role role = user.getRole();

        User person = new User();
        person.setLogin(username);
        person.setPassword(passwordEncoder.encode(password));
        person.setPerm("ROLE_" + role.name());
        if ((!role.name().equals("EMPLOYER"))&&(!role.name().equals("ADMIN"))) {
            Applicant applicant = new Applicant();
            applicant.setUser(person);
            applicantRepository.save(applicant);
        } if((!role.name().equals("APPLICANT"))&&(!role.name().equals("ADMIN"))) {
            Employer employer = new Employer();
            employer.setUser(person);
            employerRepository.save(employer);
        }
        if (role.name().equals("ADMIN")){
            repository.save(person);
        }
    }
}
