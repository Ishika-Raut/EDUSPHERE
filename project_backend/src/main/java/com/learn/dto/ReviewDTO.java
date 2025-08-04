package com.learn.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ReviewDTO 
{

    private Long id;

    @Size(max = 1000, message = "Comment can be at most 1000 characters")
    private String comment;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private int rating;

    private LocalDateTime reviewDate;

    @NotNull(message = "Learner ID is required")
    private Long learnerId;

    @NotNull(message = "Course ID is required")
    private Long courseId;
}
