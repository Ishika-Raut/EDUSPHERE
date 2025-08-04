package com.learn.service;

import java.util.List;

import com.learn.dto.ApiResponse;
import com.learn.dto.VideoDTO;

public interface VideoService 
{

	VideoDTO addVideoToCourse(VideoDTO videoDTO);

	ApiResponse  deleteVideoFromCourse(Long id);

	List<VideoDTO> getAllVideos();
	
	VideoDTO updateVideoDetails(Long id, VideoDTO videoDTO);

	VideoDTO getVideoDetailsById(Long id);
	

}
