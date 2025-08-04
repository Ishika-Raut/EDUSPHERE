package com.learn.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseWithVideosResponseDTO 
{
    private Long courseId;
    
    private String title;

    private String description;

    private double price;

    private String category;

    private String thumbnailUrl;
    
    private List<VideoDTO> videos;
}

