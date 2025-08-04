package com.learn.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learn.custom_exceptions.ApiException;
import com.learn.custom_exceptions.ResourceNotFoundException;
import com.learn.dto.ApiResponse;
import com.learn.dto.CourseDTO;
import com.learn.dto.CourseWithVideosResponseDTO;
import com.learn.dto.VideoDTO;
import com.learn.entities.Course;
import com.learn.entities.Instructor;
import com.learn.repository.CourseRepository;
import com.learn.repository.InstructorRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CourseServiceImpl implements CourseService
{
	
	// depcy	
	private final CourseRepository courseRepository;
	private final ModelMapper modelMapper;
	private InstructorRepository instructorRepository;
	
	@Override
	public CourseDTO addNewCourse(CourseDTO dto) 
	{
		/*
		 * validate if the course name already exists 
		 * -yes - throw custom exception
		 * - name alrdy exists - no continue
		 */
		if (courseRepository.findByTitle
				(dto.getTitle()).isEmpty()) 
		{
			//convert dto -> entity , for persistence
			Course entity=modelMapper.map(dto,Course.class);
			 return modelMapper.map(courseRepository.save(entity)
					 , CourseDTO.class);
		}
		throw new ApiException("Duplicate Course name !!!!!!");
	} 
	
	@Override
	public CourseDTO updateCourseDetails(Long id, CourseDTO courseDTO) 
	{
		// validate restaurant id
		Course course = courseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid course ID"));

		// valid id -> continue to update
		return modelMapper.map(courseRepository.save(course)
				 , CourseDTO.class);
	}// in case of success-> tx.commit -> DML : update -> sesison.close

	@Override
	public ApiResponse deleteCourse(Long id) 
	{
		Course course = courseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No course by the specified id!!!!!!"));
		// => course : persistent
		courseRepository.delete(course);// course : REMOVED

		return new ApiResponse("Course details deleted.....");
	}
	@Override
	public CourseDTO getCourseDetailsById(Long id) 
	{
		// TODO Auto-generated method stub
		Course entity = courseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid course ID"));
		// entity -> dto
		return modelMapper.map(entity, CourseDTO.class);
	}

	@Override
	public List<CourseDTO> getAllCourses() 
	{
		List<Course> courses = courseRepository.findAll();	//List<Course>

	    if (courses.isEmpty()) 
	    {
	        throw new ApiException("No courses found.");
	    }
		return courseRepository.findAll() //List<Course>
				.stream() //Stream<Course>
				.map(course -> modelMapper.map(course, CourseDTO.class)) //Stream<dto>
				.collect(Collectors.toList());//List<DTO>				
	}
	
	@Override
	public CourseWithVideosResponseDTO getCourseDetailsWithVideos(String title) 
	{
        Course course = courseRepository.findByTitle(title)
                .orElseThrow(() -> new ApiException("Course not found with title: " + title));

        CourseWithVideosResponseDTO dto = new CourseWithVideosResponseDTO();
        dto.setCourseId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setPrice(course.getPrice());
        dto.setCategory(course.getCategory());
        dto.setThumbnailUrl(course.getThumbnailUrl());

        List<VideoDTO> videoDTOs = course.getVideos().stream()
                .map(video -> modelMapper.map(video, VideoDTO.class))
                .collect(Collectors.toList());

        dto.setVideos(videoDTOs);
        return dto;
    }
	
	@Override
	public List<CourseWithVideosResponseDTO> getAllCoursesWithVideos() 
	{
	    List<Course> courses = courseRepository.findAll();

	    if (courses.isEmpty()) 
	    {
	        throw new ApiException("No courses found.");
	    }
	    
	    return courses.stream().map(course -> {
	        CourseWithVideosResponseDTO dto = new CourseWithVideosResponseDTO();
	        dto.setCourseId(course.getId());
	        dto.setTitle(course.getTitle());
	        dto.setDescription(course.getDescription());
	        dto.setPrice(course.getPrice());
	        dto.setCategory(course.getCategory());
	        dto.setThumbnailUrl(course.getThumbnailUrl());

	        List<VideoDTO> videoDTOs = course.getVideos().stream()
	                .map(video -> modelMapper.map(video, VideoDTO.class))
	                .collect(Collectors.toList());

	        dto.setVideos(videoDTOs);
	        return dto;
	    }).collect(Collectors.toList());
	}

	@Override
	public List<CourseDTO> getAllCoursesByInstructor(Long instructorId) {
	    Instructor instructor = instructorRepository.findById(instructorId)
	        .orElseThrow(() -> new RuntimeException("Instructor not found with id: " + instructorId));

	    List<Course> courses = instructor.getCreatedCourses(); // assuming this returns List<Course>

	    // Convert List<Course> to List<CourseDTO> using ModelMapper
	    return courses.stream()
	            .map(course -> modelMapper.map(course, CourseDTO.class))
	            .collect(Collectors.toList());
	}

	/*
	@Override
    public VideoProgress getVideoProgress(Long learnerId, Long courseId) {
        return videoProgressService.getProgressByCourse(learnerId, courseId);
    }
	*/
}
