package com.tms.springsecurity.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "applicants")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private Boolean vca;

@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employer_id")
    @ToString.Exclude private Employer employer;
@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
@ToString.Exclude List<Language> languages = new ArrayList<>();
public void addLang(Language language){
    languages.add(language);
    language.getApplicants().add(this);
}
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @ToString.Exclude List<Speciality> specialities = new ArrayList<>();
    public void addSpec(Speciality speciality){
        specialities.add(speciality);
        speciality.getApplicants().add(this);
    }


@OneToOne

    @JoinColumn(name = "user_id")
@ToString.Exclude
    private User user;
}