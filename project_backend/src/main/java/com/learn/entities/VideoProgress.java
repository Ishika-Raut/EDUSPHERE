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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "video_progress") 
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VideoProgress 
{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "learner_id", nullable = false, unique = true)
    private Learner learner;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false, unique = true)
    private Video video;

    private boolean completed = false;

    @Column(name = "watched_on")
    private LocalDateTime watchedOn;
}


