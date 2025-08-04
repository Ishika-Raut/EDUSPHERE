package com.learn.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter //used during  de- ser (DTO->Entity)
@Setter //used during  ser (Entity -> DTO)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VideoDTO 
{		
	private Long id; // Optional in response

    @NotBlank(message = "Video title is required")
    @Size(max = 255, message = "Title cannot be longer than 255 characters")
    private String title;

    @NotBlank(message = "Video URL is required")
    @Size(max = 500, message = "Video URL cannot exceed 500 characters")
    @Pattern(
    	    regexp = "^(https?://)([\\w.-]+)(:[0-9]+)?(/[\\w./%-]*)?(\\?[\\w=&%-]*)?(#[\\w-]*)?$",
    	    message = "Video URL must be a valid URL starting with http:// or https://"
    	)
    private String videoUrl;


    @Size(max = 1000, message = "Description cannot be longer than 1000 characters")
    private String description;
    
    @Size(max = 500, message = "Thumbnail URL cannot exceed 500 characters")
    @Pattern(
    	    regexp = "^(https?://).+\\.(jpg|jpeg|png|gif|webp)$",
    	    flags = Pattern.Flag.CASE_INSENSITIVE,
    	    message = "Thumbnail URL must be a valid image URL starting with http:// or https:// and ending with .jpg/.png/.gif etc."
    	)
    private String thumbnailUrl;

    @NotNull(message = "Duration is required")
    @Min(value = 10, message = "Duration must be at least 10 minutes")
    private Integer durationInMinutes;

    @NotNull(message = "Sequence order must be provided")
    @Min(value = 1, message = "Sequence order must be 1 or more")
    private Integer sequenceOrder;

    @NotNull(message = "Course ID must not be null")
    private Long courseId;
    
}
