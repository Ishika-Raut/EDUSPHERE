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
import com.learn.dto.VideoDTO;
import com.learn.service.VideoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/videos")
@AllArgsConstructor
@Validated
public class VideoController 
{

    private final VideoService videoService;

    @PostMapping("/add")
    public ResponseEntity<?> addNewVideo(@Valid @RequestBody VideoDTO videoDTO) 
    {
    	try 
    	{
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(videoService.addVideoToCourse(videoDTO));
		} 
		catch (RuntimeException e) 
    	{
			return ResponseEntity.status
				(HttpStatus.CONFLICT)
					.body(new ApiResponse(e.getMessage()));
		}
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateVideo(@PathVariable @NotNull @Min(1) Long id, @Valid @RequestBody VideoDTO videoDTO) 
    {
    	try 
    	{
			// invoke service layer method
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(videoService.updateVideoDetails(id, videoDTO));
		} 
		catch (RuntimeException e) 
    	{
			//in case of dup video name either 400 or 409 => conflict
			return ResponseEntity.status
				(HttpStatus.CONFLICT)
					.body(new ApiResponse(e.getMessage()));
		}
    }
    
    @DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteVideo(@PathVariable Long id) 
	{
		try 
		{
			return ResponseEntity.ok(videoService.deleteVideoFromCourse(id));
		} 
		catch (RuntimeException e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage()));
		}
	}
	
	@GetMapping("/getvideobyid/{id}")
	public ResponseEntity<?> getVideoDetailsById(@PathVariable @NotNull @Min(1) @Max(100) Long id) 
	{
		try 
		{
			return ResponseEntity.ok(videoService.getVideoDetailsById(id));
		}
		catch (RuntimeException e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
    
	@GetMapping
	public /* @ResponseBody */ ResponseEntity<?> getAllVideos() 
	{
		List<VideoDTO> videos = videoService.getAllVideos();
		/*
		 * in case of no videos - SC 204 , NO 
		 * in case of videos - SC 200 + List - resp body
		 */
		if (videos.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();	// Resp packet - SC 204 , NO resp body !!!!!
		
		// => videos exist
		return ResponseEntity.ok(videos);
		// resp pkt - SC 200 , body - JSON representation of list

	}
  
}

