package com.learn.service;

import java.util.List;

import com.learn.dto.ApiResponse;
import com.learn.dto.CourseDTO;
import com.learn.dto.CourseWithVideosResponseDTO;

public interface CourseService {

	CourseDTO addNewCourse(CourseDTO courseDTO);

	CourseDTO updateCourseDetails(Long id, CourseDTO courseDTO);

	ApiResponse deleteCourse(Long id);

	List<CourseDTO> getAllCourses();

	CourseDTO getCourseDetailsById(Long id);

	CourseWithVideosResponseDTO getCourseDetailsWithVideos(String title);
	
	List<CourseDTO> getAllCoursesByInstructor(Long instructorId);
	
	List<CourseWithVideosResponseDTO> getAllCoursesWithVideos();

}
