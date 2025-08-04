package com.learn.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class Certificate 
{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_url")
    private String fileUrl;
    
    @ManyToOne
    @JoinColumn(name = "learner_id", nullable = false, unique = true)
    private Learner learner;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false, unique = true)
    private Course course;
    
    @Column(name = "ceritficate_url")
    private String certificateUrl;

    @Column(name = "issued_date")
    private LocalDateTime issuedDate;
    
}



