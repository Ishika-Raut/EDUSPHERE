package com.learn.service;

import java.util.List;

import com.learn.dto.CertificateDTO;
import com.learn.dto.JobSuggestionDTO;


public interface CertificateService {

	CertificateDTO generateCertificate(Long learnerId, Long courseId);

	List<CertificateDTO> getCertificatesByLearner(Long learnerId);

	byte[] downloadCertificateFile(Long certificateId);

	List<JobSuggestionDTO> getJobSuggestionsForCertificate(Long certificateId);
	
}
