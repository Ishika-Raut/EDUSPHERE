package com.learn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.dto.ApiResponse;
import com.learn.service.LearnerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/learners")
@AllArgsConstructor
@Validated
public class LearnerController 
{

    private final LearnerService learnerService;

    // Get All Learners
    @GetMapping
    public ResponseEntity<?> getAllLearners() 
    {
    	try 
		{
			return ResponseEntity.ok(learnerService.getAllLearners());
		} 
		catch (RuntimeException e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage()));
		}
    }


}
