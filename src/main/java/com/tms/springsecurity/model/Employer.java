package com.tms.springsecurity.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employers")
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String BTV;
    private String phone;
    private String city;
    private String contactName;

    @OneToMany(mappedBy = "employer", fetch = FetchType.EAGER)
    @ToString.Exclude
    List<Applicant> applicants = new ArrayList<>();

    public void addApplicant(Applicant applicant) {
        applicants.add(applicant);
        applicant.setEmployer(this);
    }

    @OneToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;
}

