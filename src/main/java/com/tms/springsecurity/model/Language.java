package com.tms.springsecurity.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "languages")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;


   @ManyToMany(mappedBy = "languages",fetch = FetchType.EAGER)

   @ToString.Exclude private List<Applicant> applicants = new ArrayList<>();
}
