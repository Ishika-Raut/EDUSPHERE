package com.learn.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.learn.entities.*;

import com.learn.dto.CourseDTO;
import com.learn.entities.Course;
import com.learn.repository.CourseRepository;
import com.learn.repository.InstructorRepository;

import lombok.AllArgsConstructor;


@Service
@Transactional
@AllArgsConstructor
public class InstructorServiceImpl implements InstructorService {

	private final InstructorRepository instructorRepository;
	
	private final CourseRepository courseRepository;
	
	private final ModelMapper modelMapper;
	
	@Override
	public Course addCourse(Long instructorId, CourseDTO dto) {
		// TODO Auto-generated method stub
		Instructor instructor = instructorRepository.findById(instructorId)
				.orElseThrow(()-> new RuntimeException("Instructor not found"));
		
		Course course = modelMapper.map(dto, Course.class);
		course.setInstructor(instructor);
		
		return courseRepository.save(course);
	}

	@Override
	public Course updateCourse(Long courseId, CourseDTO dto) {
		// TODO Auto-generated method stub
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new RuntimeException("Course not found"));
		
		modelMapper.map(dto, course);
		return courseRepository.save(course);
	}

	@Override
	public String deleteCourse(Long courseId) {
		// TODO Auto-generated method stub
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new RuntimeException("Course not found"));
		
		courseRepository.delete(course);
		
		return "Course with ID: " + courseId + "deleted successfully";
	}

	@Override
	public List<Course> getAllCoursesByInstructor(Long instructorId) {
		// TODO Auto-generated method stub
		Instructor instructor = instructorRepository.findById(instructorId)
				.orElseThrow(() -> new RuntimeException("Instructor not found"));
		
		return instructor.getCreatedCourses();
	}
	
}
