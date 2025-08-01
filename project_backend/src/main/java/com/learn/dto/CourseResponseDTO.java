package com.learn.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CourseResponseDTO 
{
	private String title;
    private String description;
    private double price;
    private String category;
    private String thumbnailUrl;
}
