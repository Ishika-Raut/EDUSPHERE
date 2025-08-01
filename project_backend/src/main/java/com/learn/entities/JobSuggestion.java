package com.learn.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "job_suggestions") 
@NoArgsConstructor
@AllArgsConstructor
public class JobSuggestion {
    @Id @GeneratedValue
    private Long id;

    private String jobTitle;
    private String company;
    private String location;
    private String jobUrl;

    @ManyToMany(mappedBy = "jobSuggestions")
    private List<Certificate> matchedCertificates = new ArrayList<>();
}
