package com.learn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.dto.ApiResponse;
import com.learn.dto.VideoProgressDTO;
import com.learn.service.VideoProgressService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/video-progress")
@AllArgsConstructor
@Validated
public class VideoProgressController 
{

    private VideoProgressService videoProgressService;

    @PostMapping("/update")
    public ResponseEntity<?> updateProgress(@Valid @RequestBody VideoProgressDTO dto) 
    {
        try 
		{
        	return ResponseEntity.status(HttpStatus.CREATED)
					.body(videoProgressService.updateProgress(dto));
		} 
		catch (RuntimeException e) 
		{
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new ApiResponse(e.getMessage()));
		}
    }

    @GetMapping
    public ResponseEntity<?> getProgress(@RequestParam @NotNull @Min(1) Long learnerId,
                                         @RequestParam @NotNull @Min(1) Long videoId) 
    {
    	try 
		{
        	return ResponseEntity.ok(videoProgressService.getProgress(learnerId, videoId));
		} 
		catch (RuntimeException e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage()));
		}
    }

}

