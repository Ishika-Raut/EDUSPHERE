package com.learn.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VideoProgressDTO 
{

    private Long id;

    @NotNull(message = "Learner ID is required")
    private Long learnerId;

    @NotNull(message = "Video ID is required")
    private Long videoId;

    private boolean completed;

    private LocalDateTime watchedOn;

}
