package com.learn.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.dto.CourseDTO;
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
	@PreAuthorize("hasRole('INSTRUCTOR')")
	public ResponseEntity<CourseDTO> addCourse(@PathVariable Long id, @RequestBody CourseDTO dto){
		Course created = instructorService.addCourse(id,dto);
		CourseDTO responseDto = modelMapper.map(created, CourseDTO.class);	
		return ResponseEntity.ok(responseDto);
	}
	
	@PutMapping("/courses/{courseId}")

	@PreAuthorize("hasRole('INSTRUCTOR')")
	public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long courseId , @RequestBody CourseDTO dto){
		Course updated = instructorService.updateCourse(courseId,dto);
		CourseDTO responseDto = modelMapper.map(updated, CourseDTO.class);
		return ResponseEntity.ok(responseDto);
	}
	
	@GetMapping("{id}/courses")

	@PreAuthorize("hasAuthority('INSTRUCTOR') or hasAuthority('ADMIN')")
	public ResponseEntity<List<CourseDTO>> getAllCourseByInstructor(@PathVariable Long id){
		List<Course> courses = instructorService.getAllCoursesByInstructor(id);
		List<CourseDTO> dtos = courses.stream()
				.map(course -> modelMapper.map(course, CourseDTO.class))
				.toList();
		return ResponseEntity.ok(dtos);
	}
	
	@DeleteMapping("/courses/{courseId}")
	@PreAuthorize("hasRole('INSTRUCTOR')")
	public ResponseEntity<String> deleteCourse(@PathVariable Long courseId) {
	    instructorService.deleteCourse(courseId);
	    return ResponseEntity.ok("Course deleted successfully");
	}

}
