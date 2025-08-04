package com.learn.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.learn.dto.EnrollmentRequestDTO;
import com.learn.dto.EnrollmentResponseDTO;
import com.learn.entities.Course;
import com.learn.entities.Enrollment;
import com.learn.entities.User;
import com.learn.repository.CourseRepository;
import com.learn.repository.EnrollmentRepository;
import com.learn.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService
{

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public EnrollmentResponseDTO enrollLearner(EnrollmentRequestDTO dto) 
    {
        User student = userRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setLearner(student);
        enrollment.setCourse(course);
        enrollment.setEnrolledDate(dto.getEnrolledAt());
        enrollment.setCompleted(dto.isCompleted());

        Enrollment saved = enrollmentRepository.save(enrollment);
        return mapToDTO(saved);
    }

    public List<EnrollmentResponseDTO> getAllEnrollments() 
    {
        return enrollmentRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public EnrollmentResponseDTO getEnrollmentById(Long id) 
    {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
        return mapToDTO(enrollment);
    }

    public EnrollmentResponseDTO updateEnrollment(Long id, EnrollmentRequestDTO dto) 
    {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        User student = userRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        enrollment.setLearner(student);
        enrollment.setCourse(course);
        enrollment.setEnrolledDate(dto.getEnrolledAt());
        enrollment.setCompleted(dto.isCompleted());

        Enrollment updated = enrollmentRepository.save(enrollment);
        return mapToDTO(updated);
    }

    public void deleteEnrollment(Long id) 
    {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
        enrollmentRepository.delete(enrollment);
    }

    private EnrollmentResponseDTO mapToDTO(Enrollment enrollment) {
        EnrollmentResponseDTO dto = new EnrollmentResponseDTO();
        dto.setId(enrollment.getId());
        dto.setStudentId(enrollment.getLearner().getId());
        dto.setStudentName(enrollment.getLearner().getFullName());
        dto.setCourseId(enrollment.getCourse().getId());
        dto.setCourseTitle(enrollment.getCourse().getTitle());
        dto.setEnrolledAt(enrollment.getEnrolledDate().toString());
        dto.setCompleted(enrollment.getCompleted());
        return dto;
    }
    
    /*
    @Override
    public List<Course> getEnrolledCourses(Long learnerId) {
        return enrollmentService.getCoursesByLearnerId(learnerId);
    }
    */
}
