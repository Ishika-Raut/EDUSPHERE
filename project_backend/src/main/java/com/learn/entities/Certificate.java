package com.learn.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "certificate") 
@NoArgsConstructor
@AllArgsConstructor
public class Certificate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileUrl;
    private LocalDateTime issuedAt;

    @ManyToOne
    @JoinColumn(name = "learner_id",nullable = false)
    private Learner learner;

    @ManyToOne
    @JoinColumn(name = "course_id",nullable = false)
    private Course course;
    
    private String certificateUrl;
    private LocalDateTime issuedDate;

    @ManyToMany
    private List<JobSuggestion> jobSuggestions = new ArrayList<>();
}

