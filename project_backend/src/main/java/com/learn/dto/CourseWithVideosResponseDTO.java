package com.learn.dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CourseWithVideosResponseDTO {
    private Long courseId;
    private String title;
    private String description;
    private List<VideoResponseDTO> videos;
}

