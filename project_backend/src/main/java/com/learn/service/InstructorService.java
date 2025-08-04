package com.learn.service;

import java.util.List;


import com.learn.dto.CourseDTO;
import com.learn.entities.Course;

public interface InstructorService {

	Course addCourse(Long id, CourseDTO dto);
	
	Course updateCourse(Long courseId,CourseDTO dto);

	String deleteCourse(Long courseId);
	
	List<Course> getAllCoursesByInstructor(Long instructorId);

}
