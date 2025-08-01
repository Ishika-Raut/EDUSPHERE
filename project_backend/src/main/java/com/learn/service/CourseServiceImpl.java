package com.learn.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learn.custom_exceptions.ApiException;
import com.learn.custom_exceptions.ResourceNotFoundException;
import com.learn.dto.ApiResponse;
import com.learn.dto.CourseRequestDTO;
import com.learn.dto.CourseResponseDTO;
import com.learn.dto.CourseWithVideosResponseDTO;
import com.learn.dto.VideoResponseDTO;
import com.learn.entities.Course;
import com.learn.entities.Video;
import com.learn.repository.CourseRepository;
import com.learn.repository.VideoRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CourseServiceImpl implements CourseService
{
	
	// depcy	
	private final CourseRepository courseRepository;
	private final ModelMapper modelMapper;

	@Override
	public List<CourseResponseDTO> getAllCourses() 
	{
		List<Course> courses = courseRepository.findAll();	//List<Course>

	    if (courses.isEmpty()) 
	    {
	        throw new ApiException("No courses found.");
	    }
		return courseRepository.findAll() //List<Course>
				.stream() //Stream<Course>
				.map(course -> modelMapper.map(course, CourseResponseDTO.class)) //Stream<dto>
				.collect(Collectors.toList());//List<DTO>				
	}
		
	@Override
	public CourseResponseDTO addNewCourse(CourseRequestDTO dto) 
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
					 , CourseResponseDTO.class);
		}
		throw new ApiException("Dup Course name !!!!!!");
	} // when transactional method rets - tx over - tx .commit
		// service layer rets DETACHED entity to the caller

	@Override
	public CourseResponseDTO getCourseDetailsById(Long id) 
	{
		// TODO Auto-generated method stub
		Course entity = courseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid course ID"));
		// entity -> dto
		return modelMapper.map(entity, CourseResponseDTO.class);
	}

	@Override
	public Course updateCourseDetails(Long id, CourseRequestDTO courseDTO) 
	{
		// validate restaurant id
		Course course = courseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid course ID"));

		// valid id -> continue to update
		return courseRepository.save(course);
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
	public CourseWithVideosResponseDTO getCourseWithVideos(String title) 
	{
        Course course = courseRepository.findByTitle(title)
                .orElseThrow(() -> new ApiException("Course not found with title: " + title));

        CourseWithVideosResponseDTO dto = new CourseWithVideosResponseDTO();
        //dto.setCourseId(course.getId);
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());

        List<VideoResponseDTO> videoDTOs = course.getVideos().stream()
                .map(video -> modelMapper.map(video, VideoResponseDTO.class))
                .collect(Collectors.toList());

        dto.setVideos(videoDTOs);
        return dto;
    }
	
	@Override
	public List<CourseWithVideosResponseDTO> getAllCoursesWithVideos() 
	{
	    List<Course> courses = courseRepository.findAll();

	    return courses.stream().map(course -> {
	        CourseWithVideosResponseDTO dto = new CourseWithVideosResponseDTO();
	        //dto.setCourseId(course.getId());
	        dto.setTitle(course.getTitle());
	        dto.setDescription(course.getDescription());

	        List<VideoResponseDTO> videoDTOs = course.getVideos().stream()
	                .map(video -> modelMapper.map(video, VideoResponseDTO.class))
	                .collect(Collectors.toList());

	        dto.setVideos(videoDTOs);
	        return dto;
	    }).collect(Collectors.toList());
	}
	// no exc -> commit -> DML - delete . session.close -> l1 cache -> TRANSIENT

}
