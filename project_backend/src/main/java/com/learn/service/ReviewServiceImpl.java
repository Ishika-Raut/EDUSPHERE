package com.learn.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.learn.custom_exceptions.ResourceNotFoundException;
import com.learn.dto.ReviewDTO;
import com.learn.entities.Course;
import com.learn.entities.Learner;
import com.learn.entities.Review;
import com.learn.repository.CourseRepository;
import com.learn.repository.LearnerRepository;
import com.learn.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService 
{

    private final ReviewRepository reviewRepository;
    private final LearnerRepository learnerRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    @Override
    public ReviewDTO createReview(ReviewDTO dto) 
    {
        Learner learner = learnerRepository.findById(dto.getLearnerId())
                .orElseThrow(() -> new ResourceNotFoundException("Learner not found"));

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        Review review = modelMapper.map(dto, Review.class);
        review.setLearner(learner);
        review.setCourse(course);
        review.setReviewDate(LocalDateTime.now());

        return modelMapper.map(reviewRepository.save(review), ReviewDTO.class);
    }

    @Override
    public ReviewDTO updateReview(Long id, ReviewDTO dto) 
    {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));

        review.setComment(dto.getComment());
        review.setRating(dto.getRating());
        review.setReviewDate(LocalDateTime.now());

        return modelMapper.map(reviewRepository.save(review), ReviewDTO.class);
    }

    @Override
    public void deleteReview(Long id) 
    {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));
        reviewRepository.delete(review);
    }

    @Override
    public ReviewDTO getReviewById(Long id) 
    {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));
        return modelMapper.map(review, ReviewDTO.class);
    }

    @Override
    public List<ReviewDTO> getAllReviewsForCourse(Long courseId) 
    {
        return reviewRepository.findByCourseId(courseId)
                .stream()
                .map(review -> modelMapper.map(review, ReviewDTO.class))
                .collect(Collectors.toList());
    }
}
