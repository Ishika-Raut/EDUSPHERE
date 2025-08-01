package com.learn.service;

import java.util.List;

import com.learn.dto.ApiResponse;
import com.learn.dto.CourseRequestDTO;
import com.learn.dto.CourseResponseDTO;
import com.learn.dto.CourseWithVideosResponseDTO;
import com.learn.entities.Course;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public interface CourseService {

	CourseResponseDTO addNewCourse(CourseRequestDTO courseDTO);

	Course updateCourseDetails(Long id, CourseRequestDTO courseDTO);

	ApiResponse deleteCourse(Long id);

	List<CourseResponseDTO> getAllCourses();

	CourseResponseDTO getCourseDetailsById(Long id);

	CourseWithVideosResponseDTO getCourseWithVideos(String title);
	
	List<CourseWithVideosResponseDTO> getAllCoursesWithVideos();

}
