package com.learn.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollmentResponseDTO 
{
    private Long id;
    private Long studentId;
    private String studentName;
    private Long courseId;
    private String courseTitle;
    private String enrolledAt;
    private boolean completed;
}
