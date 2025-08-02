package com.learn.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.dto.CourseResponseDTO;
import com.learn.entities.Course;
import com.learn.service.CourseService;
import com.learn.service.InstructorService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/instructor")
@AllArgsConstructor
public class InstructorController {
	
	private final InstructorService instructorService;
	
	private final ModelMapper modelMapper;
	
	private final CourseService courseService;

	@PostMapping("/{id}/courses")
	public ResponseEntity<CourseResponseDTO> addCourse(@PathVariable Long id, @RequestBody CourseResponseDTO dto){
		Course created = instructorService.addCourse(id,dto);
		CourseResponseDTO responseDto = modelMapper.map(created, CourseResponseDTO.class);	
		return ResponseEntity.ok(responseDto);
	}
	
	@PutMapping("/courses/{courseId}")
	public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable Long courseId , @RequestBody CourseResponseDTO dto){
		Course updated = instructorService.updateCourse(courseId,dto);
		CourseResponseDTO responseDto = modelMapper.map(updated, CourseResponseDTO.class);
		return ResponseEntity.ok(responseDto);
	}
	
	@GetMapping("{id}/courses")
	public ResponseEntity<List<CourseResponseDTO>> getAllCourseByInstructor(@PathVariable Long id){
		List<Course> courses = instructorService.getAllCoursesByInstructor(id);
		List<CourseResponseDTO> dtos = courses.stream()
				.map(course -> modelMapper.map(course, CourseResponseDTO.class))
				.toList();
		return ResponseEntity.ok(dtos);
	}
	
	@DeleteMapping("/courses/{courseId}")
	public ResponseEntity<String> deleteCourse(@PathVariable Long courseId) {
	    instructorService.deleteCourse(courseId);
	    return ResponseEntity.ok("Course deleted successfully");
	}

}
