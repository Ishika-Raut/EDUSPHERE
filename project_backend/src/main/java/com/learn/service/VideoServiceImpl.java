package com.learn.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learn.custom_exceptions.ApiException;
import com.learn.custom_exceptions.ResourceNotFoundException;
import com.learn.dto.ApiResponse;
import com.learn.dto.VideoDTO;
import com.learn.entities.Course;
import com.learn.entities.Video;
import com.learn.repository.CourseRepository;
import com.learn.repository.VideoRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class VideoServiceImpl implements VideoService 
{

	// depcy	
	private final VideoRepository videoRepository;
	private final CourseRepository courseRepository;
	private final ModelMapper modelMapper;

	//Add
	@Override
	public VideoDTO addVideoToCourse(VideoDTO videoDTO) 
	{

	    Course course = courseRepository.findById(videoDTO.getCourseId())
	        .orElseThrow(() -> new ApiException("Course not found with Id: " + videoDTO.getCourseId()));

	    Optional<Video> existingVideo = videoRepository.findByVideoUrl(videoDTO.getVideoUrl());

	    if (existingVideo.isPresent()) 
	    {
	        throw new ApiException("Video with this URL already exists: " + videoDTO.getVideoUrl());
	    }

	    Video entity = modelMapper.map(videoDTO, Video.class);

	    entity.setCourse(course); // This sets course_id FK in video table

	    Video savedVideo = videoRepository.save(entity);
	    return modelMapper.map(savedVideo, VideoDTO.class);
	   
	}
	
	//Update
	@Override
	public VideoDTO updateVideoDetails(Long id, VideoDTO videoDTO) 
	{

	    Video video = null;

	    if (id != null) 
	    {
	        video = videoRepository.findById(id)
	                .orElseThrow(() -> new ApiException("Video not found with ID: " + id));
	    } 
	    else if (videoDTO.getVideoUrl() != null) 
	    {
	        video = videoRepository.findByVideoUrl(videoDTO.getVideoUrl())
	                .orElseThrow(() -> new ApiException("Video not found with URL: " + videoDTO.getVideoUrl()));
	    }
	    else 
	    {
	        throw new ApiException("Either video ID or video URL must be provided to update video.");
	    }

	    Course course = courseRepository.findById(videoDTO.getId())
	            .orElseThrow(() -> new ApiException("Course not found with name: " + videoDTO.getCourseId()));

	    modelMapper.map(videoDTO, video);
	    
	    Video updatedVideo = videoRepository.save(video);
	    return modelMapper.map(updatedVideo, VideoDTO.class);

	}

	//Delete
	@Override
	public ApiResponse deleteVideoFromCourse(Long id) 
	{
		Video video = videoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No video by the specified id!!!!!!"));
		// => video : persistent
		videoRepository.delete(video);// video : REMOVED

		return new ApiResponse("Video details deleted.....");
	}
	

	//Get By id
	@Override
	public VideoDTO getVideoDetailsById(Long videoId) 
	{
		Video entity = videoRepository.findById(videoId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid video ID"));
		// entity -> dto
		return modelMapper.map
				(entity, VideoDTO.class);
	}

	//Get All videos
	@Override
	public List<VideoDTO> getAllVideos() {
		
		List<Video> videos = videoRepository.findAll();	//List<Video>

	    if (videos.isEmpty()) {
	        throw new ApiException("No videos found.");
	    }
		return videos.stream() //Stream<Video>
		.map(restaurant -> modelMapper.map(restaurant, VideoDTO.class)) //Stream<dto>
		.collect(Collectors.toList());//List<DTO>				
	}
	
}
