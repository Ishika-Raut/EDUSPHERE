package com.learn.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.dto.CertificateDTO;
import com.learn.dto.JobSuggestionDTO;
import com.learn.service.CertificateService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/certificates")
@AllArgsConstructor
public class CertificateController {
	private final CertificateService certificateService;
	
	@PostMapping("/generate/{learnerId}/{courseId}")
	public ResponseEntity<CertificateDTO> generateCertificte(@PathVariable Long learnerId,@PathVariable Long courseId){
		CertificateDTO dto = certificateService.generateCertificate(learnerId,courseId);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/learner/{learnerId}")
	@PreAuthorize("hasRole('LEARNER')")
	public ResponseEntity<List<CertificateDTO>> getLearnerCertificates(@PathVariable Long learnerId){
		return ResponseEntity.ok(certificateService.getCertificatesByLearner(learnerId));
	}
	
	@GetMapping("/download/{certificateId}")
	@PreAuthorize("hasRole('LEARNER')")
	public ResponseEntity<byte[]> download(@PathVariable Long certificateId) {
		byte[] content = certificateService.downloadCertificateFile(certificateId);
		return ResponseEntity.ok()
				.header("Content-Disposition", "attachment; filename=certificate- " + certificateId + ".txt")
				.body(content);
	}
	
	@GetMapping("/{certificateId}/jobSuggestions")
	@PreAuthorize("hasRole('LEARNER')")
	public ResponseEntity<List<JobSuggestionDTO>> getJobSuggestionsForCertificate(@PathVariable Long certificateId){
		List<JobSuggestionDTO> suggestions = certificateService.getJobSuggestionsForCertificate(certificateId);
		return ResponseEntity.ok(suggestions);
	}
}
