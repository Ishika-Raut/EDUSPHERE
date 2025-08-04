package com.learn.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "job_suggestions") 
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JobSuggestion 
{
    
    @Id 
    @GeneratedValue
    private Long id;

    private String jobTitle;

    private String company;

    private String location;
    
    private String jobUrl;
    
    @Column(name = "suggested_date")
    private LocalDateTime suggestedDate;

}
