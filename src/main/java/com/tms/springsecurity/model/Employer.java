package com.tms.springsecurity.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.Builder.Default;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "employers")
public class Employer {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String BTV;
    private String phone;
    private String city;
    private String contactName;

    @OneToMany(mappedBy = "employer",fetch = FetchType.EAGER)
    @ToString.Exclude
    List<Applicant> applicants = new ArrayList<>();

    public Employer(int i, String employer, String number, String s, String city, String contact) {

    }

    public void addApplicant(Applicant applicant) {
        applicants.add(applicant);
        applicant.setEmployer(this);
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;
}

