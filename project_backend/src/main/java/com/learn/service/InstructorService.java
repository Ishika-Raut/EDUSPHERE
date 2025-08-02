package com.learn.service;

import java.util.List;

import com.learn.dto.CourseResponseDTO;
import com.learn.entities.Course;

public interface InstructorService {

	Course addCourse(Long id, CourseResponseDTO dto);
	
	Course updateCourse(Long courseId,CourseResponseDTO dto);
	
	String deleteCourse(Long courseId);
	
	List<Course> getAllCoursesByInstructor(Long instructorId);

}
