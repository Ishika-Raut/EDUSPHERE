package com.learn.service;

import java.util.List;

import com.learn.dto.ReviewDTO;

public interface ReviewService 
{
    ReviewDTO createReview(ReviewDTO reviewDTO);
    
    ReviewDTO updateReview(Long id, ReviewDTO reviewDTO);
    
    void deleteReview(Long id);
    
    ReviewDTO getReviewById(Long id);
    
    List<ReviewDTO> getAllReviewsForCourse(Long courseId);
}

