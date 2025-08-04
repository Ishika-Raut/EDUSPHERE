package com.learn.controller;

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
import com.learn.dto.LearnerDTO;
import com.learn.service.LearnerService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/learners")
@AllArgsConstructor
@Validated
public class LearnerController 
{

    private final LearnerService learnerService;

    // Create Learner
    @PostMapping
    public ResponseEntity<?> createLearner(@Valid @RequestBody LearnerDTO learnerDTO) 
    {
        try 
		{
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(learnerService.createLearner(learnerDTO));
		} 
		catch (RuntimeException e) 
		{
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new ApiResponse(e.getMessage()));
		}
    }

    // Get Learner by ID
    @GetMapping("/getById/{learnerId}")
    public ResponseEntity<?> getLearnerById(@PathVariable @NotNull @Min(1) Long learnerId) 
    {
        try 
		{
			return ResponseEntity.ok(learnerService.getLearnerById(learnerId));
		} 
		catch (RuntimeException e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage()));
		}
    }

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

    // Update Learner
    @PutMapping("/update/{learnerId}")
    public ResponseEntity<?> updateLearner(@PathVariable Long learnerId, @Valid @RequestBody LearnerDTO learnerDTO) 
    {
        try 
		{
        	return ResponseEntity.status(HttpStatus.CREATED)
					.body(learnerService.updateLearner(learnerId, learnerDTO));
		} 
		catch (RuntimeException e) 
		{
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new ApiResponse(e.getMessage()));
		}
    }

    // Delete Learner
    @DeleteMapping("/delete/{learnerId}")
    public ResponseEntity<?> deleteLearner(@PathVariable @NotNull @Min(1) Long learnerId) 
    {
    	try 
		{
			return ResponseEntity.ok(learnerService.deleteLearner(learnerId));
		} 
		catch (RuntimeException e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage()));
		}
    }

    /*
    // Get Payment IDs of Learner
    @GetMapping("/{learnerId}/payments")
    public ResponseEntity<?> getPaymentIdsByLearner(@PathVariable Long learnerId) 
    {
        try 
		{
			return ResponseEntity.ok(learnerService.getPaymentIdsByLearner(learnerId));
		} 
		catch (RuntimeException e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage()));
		}
    }
    */

    // Get Certificate IDs of Learner
    @GetMapping("/{learnerId}/certificates")
    public ResponseEntity<?> getCertificateIdsByLearner(@PathVariable @NotNull @Min(1) Long learnerId) 
    {
        try 
		{
			return ResponseEntity.ok(learnerService.getCertificateIdsByLearner(learnerId));
		} 
		catch (RuntimeException e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage()));
		}
    }
    
    // Get Reviews IDs of Learner
    @GetMapping("/{learnerId}/reviews")
    public ResponseEntity<?> getReviewIdsByLearner(@PathVariable @NotNull @Min(1) Long learnerId) 
    {
    	try 
		{
			return ResponseEntity.ok(learnerService.getReviewIdsByLearner(learnerId));
		} 
		catch (RuntimeException e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage()));
		}
    }

}
