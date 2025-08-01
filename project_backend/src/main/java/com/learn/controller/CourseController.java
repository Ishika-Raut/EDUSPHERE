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
import com.learn.dto.CourseRequestDTO;
import com.learn.dto.CourseResponseDTO;
import com.learn.dto.CourseWithVideosResponseDTO;
import com.learn.service.CourseService;

import jakarta.validation.constraints.Max;
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
	
	/*
	 * Desc - Add REST API endpoint to add new course 
	 * URL - http://host:port/courses/add 
	 * Method - POST 
	 * Payload - JSON representation of  CourseDTO(JSON -> Java : de serial = un marshalling - @RequestBody)
	 * Response - Body - courseDTO -> ser -> @RespBody
	 * 
	 */
    @PostMapping("/add")
    public ResponseEntity<?> addNewCourse(@RequestBody CourseRequestDTO courseDTO) 
    {
    	try 
    	{
			// invoke service layer method
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(courseService.addNewCourse(courseDTO));
		} 
		catch (RuntimeException e) 
    	{
			//in case of dup course id either 400 or 409 => conflict
			return ResponseEntity.status
				(HttpStatus.CONFLICT)
					.body(new ApiResponse(e.getMessage()));
		}
    }

    
    /*
	 * Desc - Add REST API endpoint to update course 
	 * URL - http://host:port/courses/{id} 
	 * Method - PUT 
	 * Payload - JSON representation of  CourseDTO(JSON -> Java : de serial = un marshalling - @RequestBody)
	 * Response - Body - CourseDTO -> ser -> @RespBody
	 * 
	 */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody CourseRequestDTO courseDTO) 
    {
    	try 
    	{
			// invoke service layer method
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(courseService.updateCourseDetails(id, courseDTO));
		} 
		catch (RuntimeException e) 
    	{
			//in case of dup course id either 400 or 409 => conflict
			return ResponseEntity.status
				(HttpStatus.CONFLICT)
					.body(new ApiResponse(e.getMessage()));
		}
    }
    

    /*
	 * Desc - Add REST API endpoint to delete course 
	 * URL - http://host:port/course/{id} 
	 * Method - DELETE 
	 * Payload - none
	 * success - Resp - DTO : ApiResp - SC 200 , mesg
	 * error resp - ApiResp - SC 404 ,err mesg
	 * 
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCourse(@PathVariable Long id) 
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
	
	/*
	 * Desc - Add REST API endpoint to get course by id
	 * URL - http://host:port/course/{id} 
	 * Method - GET 
	 * Payload - none
	 * Resp - JSON representation of course
	 * 
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getCourseDetailsById(@PathVariable @NotNull @Min(1) @Max(100) Long id) 
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
    
	/*
	 * Desc - Add REST API endpoint to get all courses
	 * URL - http://host:port/courses 
	 * Method - GET 
	 * Payload - none 
	 * Response - Body - List<Course>
	 * 
	 */
	@GetMapping
	public /* @ResponseBody */ ResponseEntity<?> getAllVideos() 
	{
		List<CourseResponseDTO> courses = courseService.getAllCourses();
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
	
	/*
	 * Desc - Add REST API endpoint to get course with all videos
	 * URL - http://host:port/courses/getCourseWithVideos/{title} 
	 * Method - GET 
	 * Payload - none 
	 * Response - Body - List<CourseWithAllVideo>
	 * 
	 */
	@GetMapping("/getCourseWithVideos/{title}")
	public ResponseEntity<CourseWithVideosResponseDTO> getCourseWithVideos(@PathVariable String title) {
	    return ResponseEntity.ok(courseService.getCourseWithVideos(title));
	}

	/*
	 * Desc - Add REST API endpoint to get all course with all videos
	 * URL - http://host:port/courses/getAllCourseWithVideos/{title} 
	 * Method - GET 
	 * Payload - none 
	 * Response - Body - List<AllCourseWithAllVideo>
	 * 
	 */
	@GetMapping("/getAllCoursesWithVideos")
	public ResponseEntity<List<CourseWithVideosResponseDTO>> getAllCoursesWithVideos() {
	    return ResponseEntity.ok(courseService.getAllCoursesWithVideos());
	}

  
}
