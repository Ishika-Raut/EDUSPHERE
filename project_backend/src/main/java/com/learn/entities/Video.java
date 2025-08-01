package com.learn.entities;

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
@Table(name = "videos") 
@NoArgsConstructor
@AllArgsConstructor
public class Video 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String videoUrl;
    private String description;
    private int durationInMinutes;
    //private boolean isPreview;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

}

