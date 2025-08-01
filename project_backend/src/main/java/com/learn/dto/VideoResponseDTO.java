package com.learn.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter //used during  de- ser (DTO->Entity)
@Setter //used during  ser (Entity -> DTO)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VideoResponseDTO 
{		
	private String title;
    private String description;
    private String videoUrl;
    private int durationInMinutes;
    private String courseName;
}
