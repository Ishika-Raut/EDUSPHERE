package com.learn.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseRequestDTO 
{
	private String title;
    private String description;
    private double price;
    private String category;
    private String thumbnailUrl;

}
