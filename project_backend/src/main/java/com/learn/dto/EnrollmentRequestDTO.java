package com.learn.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EnrollmentRequestDTO 
{
	
    private Long studentId;
    private Long courseId;
    private LocalDateTime enrolledAt;
    private boolean completed;
}
