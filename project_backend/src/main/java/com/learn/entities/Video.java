package com.learn.entities;

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
@Table(name = "video") 
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Video 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private String title;

	@Column(name = "video_url")
    private String videoUrl;

    private String description;
    
    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "duration_in_minutes")
    private Integer durationInMinutes;
    
    @Column(name = "sequesnce_order")
    private Integer sequenceOrder;
    
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false, unique = true)
    private Course course;

}

