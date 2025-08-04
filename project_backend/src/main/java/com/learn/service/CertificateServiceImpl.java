package com.learn.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.learn.dto.CertificateDTO;
import com.learn.dto.JobSuggestionDTO;
import com.learn.entities.Certificate;
import com.learn.entities.Course;
import com.learn.entities.JobSuggestion;
import com.learn.entities.Learner;
import com.learn.repository.CertificateRepository;
import com.learn.repository.CourseRepository;
import com.learn.repository.JobSuggestionRepository;
import com.learn.repository.LearnerRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CertificateServiceImpl implements CertificateService {

	private final CertificateRepository certificateRepository;
	private final LearnerRepository learnerRepository;
	private final CourseRepository courseRepository;
	private final JobSuggestionRepository jobSuggestionRepository;
	private final ModelMapper modelMapper;
	

	@Override
	public CertificateDTO generateCertificate(Long learnerId, Long courseId) {
		// TODO Auto-generated method stub
		Learner learner = learnerRepository.findById(learnerId)
				.orElseThrow(() -> new RuntimeException("Learner not found"));
		
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new RuntimeException("Course not found"));
		
		Certificate cert = new Certificate();
		cert.setLearner(learner);
		cert.setCourse(course);
		cert.setIssuedDate(LocalDateTime.now());
		cert.setIssuedDate(LocalDateTime.now());
		cert.setCertificateUrl("http://localhost:8080/api/certificates/download/" + learnerId + "/" + courseId);
		
		Certificate savedCert = certificateRepository.save(cert);
		
		List<JobSuggestion> matchedJobs = jobSuggestionRepository.findByCourseTag(course.getTitle());
		
		CertificateDTO certDTO = modelMapper.map(savedCert,CertificateDTO.class);
		certDTO.setLearnerId(learnerId);
		certDTO.setCourseId(courseId);
		certDTO.setJobSuggestions(matchedJobs.stream().map(job -> modelMapper.map(job, JobSuggestionDTO.class)).toList());
		return certDTO;
	}

	@Override
	public List<CertificateDTO> getCertificatesByLearner(Long learnerId) {
		// TODO Auto-generated method stub
		Learner learner = learnerRepository.findById(learnerId)
				.orElseThrow(() -> new RuntimeException("Learner not found"));
		
		List<Certificate> certs = certificateRepository.findByLearnerId(learnerId);
		
		
		return certs.stream().map(cert -> {
			CertificateDTO dto = modelMapper.map(cert, CertificateDTO.class);
			dto.setLearnerId(learnerId);
			dto.setCourseId(cert.getCourse().getId());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public byte[] downloadCertificateFile(Long certificateId) {
		// TODO Auto-generated method stub
		Certificate cert = certificateRepository.findById(certificateId)
				.orElseThrow(() -> new RuntimeException("Certificate not found"));
		
		String fakeContent = "Certificate of Completion\nLearner: " + cert.getLearner().getUser().getFullName() + 
							 "\nCourse: " + cert.getCourse().getTitle() +
							 "\nDate: " + cert.getIssuedDate();
		return fakeContent.getBytes();
	}
	
	@Override
	public List<JobSuggestionDTO> getJobSuggestionsForCertificate(Long certificateId){
		Certificate cert = certificateRepository.findById(certificateId)
				.orElseThrow(() -> new RuntimeException("Certificate not found"));
		
		String courseTitle = cert.getCourse().getTitle();
		
		List<JobSuggestion> jobs = jobSuggestionRepository.findByCourseTag(courseTitle);
		
		return jobs.stream()
				.map(job -> modelMapper.map(job, JobSuggestionDTO.class))
				.collect(Collectors.toList());
	}

}
