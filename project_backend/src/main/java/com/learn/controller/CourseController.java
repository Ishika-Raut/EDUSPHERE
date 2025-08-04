package com.learn.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.dto.ApiResponse;
import com.learn.dto.CourseDTO;
import com.learn.service.CourseService;
import com.learn.service.InstructorService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
@Validated
public class CourseController 
{

	private final CourseService courseService;
	private InstructorService instructorService;
	
    @PostMapping("/add")
    public ResponseEntity<?> addNewCourse(@Valid @RequestBody CourseDTO courseDTO) 
    {
    	try 
    	{
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(courseService.addNewCourse(courseDTO));
		} 
		catch (RuntimeException e) 
    	{
			return ResponseEntity.status
				(HttpStatus.CONFLICT)
					.body(new ApiResponse(e.getMessage()));
		}
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourseDetails(@PathVariable @NotNull @Min(1) Long id, @Valid @RequestBody CourseDTO courseDTO) 
    {
    	try 
    	{
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(courseService.updateCourseDetails(id, courseDTO));
		} 
		catch (RuntimeException e) 
    	{
			return ResponseEntity.status
				(HttpStatus.CONFLICT)
					.body(new ApiResponse(e.getMessage()));
		}
    }
    

    @DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCourse(@PathVariable @NotNull @Min(1) Long id) 
	{
		try 
		{
			return ResponseEntity.ok(courseService.deleteCourse(id));
		} 
		catch (RuntimeException e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage()));
		}
	}
	
	@GetMapping("/getcoursebyid/{id}")
	public ResponseEntity<?> getCourseDetailsById(@PathVariable @NotNull @Min(1) Long id) 
	{
		try 
		{
			return ResponseEntity.ok(courseService.getCourseDetailsById(id));
		}
		catch (RuntimeException e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
    
	@GetMapping
	public /* @ResponseBody */ ResponseEntity<?> getAllCourses() 
	{
		List<CourseDTO> courses = courseService.getAllCourses();
		/*
		 * in case of no courses - SC 204 , NO 
		 * in case of courses - SC 200 + List - resp body
		 */
		if (courses.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();	// Resp packet - SC 204 , NO resp body !!!!!
		
		// => courses exist
		return ResponseEntity.ok(courses);
		// resp pkt - SC 200 , body - JSON representation of list

	}
	
	@GetMapping("/getCourseWithVideos/{title}")
	public ResponseEntity<?> getCourseDetailsWithVideos(@PathVariable @NotNull String title) 
	{
		try 
		{
			return ResponseEntity.ok(courseService.getCourseDetailsWithVideos(title));
		}
		catch (RuntimeException e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}	
	}

	@GetMapping("/getAllCoursesWithVideos")
	public ResponseEntity<?> getAllCoursesWithVideos() 
	{
		try 
		{
			return ResponseEntity.ok(courseService.getAllCoursesWithVideos());
		}
		catch (RuntimeException e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}

	@GetMapping("/getAllCoursesByInstructor/{instructorId}")
    public ResponseEntity<?> getAllCoursesByInstructor(@PathVariable Long instructorId) 
	{
		try 
		{
			return ResponseEntity.ok(instructorService.getAllCoursesByInstructor(instructorId));
		}
		catch (RuntimeException e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
    }
  
}
