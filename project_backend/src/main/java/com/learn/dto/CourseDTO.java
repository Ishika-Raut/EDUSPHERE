package com.learn.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDTO 
{
	private Long id;
	
	@NotBlank(message = "Course title cannot be blank")
    @Size(max = 100, message = "Course title cannot exceed 100 characters")
    private String title;

    @NotBlank(message = "Course description cannot be blank")
    @Size(max = 1000, message = "Course description cannot exceed 1000 characters")
    private String description;

    @PositiveOrZero(message = "Price must be zero or positive")
    private double price;

    @NotBlank(message = "Category cannot be blank")
    @Size(max = 50, message = "Category cannot exceed 50 characters")
    private String category;

    @Size(max = 500, message = "Thumbnail URL cannot exceed 500 characters")
    @Pattern(
    	    regexp = "^(https?://).+\\.(jpg|jpeg|png|gif|webp)$",
    	    flags = Pattern.Flag.CASE_INSENSITIVE,
    	    message = "Thumbnail URL must be a valid image URL starting with http:// or https:// and ending with .jpg/.png/.gif etc."
    	)
    private String thumbnailUrl;

    @NotNull(message = "Instructor ID is required")
    private Long instructorId;
}
