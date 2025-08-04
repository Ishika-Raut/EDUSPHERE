package com.learn.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CertificateDTO {
	private Long id;
    private Long learnerId;
    private Long courseId;
    private String certificateUrl;
    private LocalDateTime issuedAt;
    private LocalDateTime issuedDate;
    private List<JobSuggestionDTO> jobSuggestions = new ArrayList<>();
	
    
}
