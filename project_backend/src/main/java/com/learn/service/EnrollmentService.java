package com.learn.service;

import java.util.List;

import com.learn.dto.EnrollmentRequestDTO;
import com.learn.dto.EnrollmentResponseDTO;

public interface EnrollmentService {

	EnrollmentResponseDTO enrollLearner(EnrollmentRequestDTO dto);

	List<EnrollmentResponseDTO> getAllEnrollments();

	EnrollmentResponseDTO getEnrollmentById(Long id);

	EnrollmentResponseDTO updateEnrollment(Long id, EnrollmentRequestDTO dto);

	void deleteEnrollment(Long id);

}
