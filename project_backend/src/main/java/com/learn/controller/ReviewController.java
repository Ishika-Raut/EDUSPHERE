package com.learn.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learn.dto.ReviewDTO;
import com.learn.service.ReviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController 
{

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewDTO> create(@Valid @RequestBody ReviewDTO dto) {
        return ResponseEntity.ok(reviewService.createReview(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> update(@PathVariable Long id, @Valid @RequestBody ReviewDTO dto) {
        return ResponseEntity.ok(reviewService.updateReview(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<ReviewDTO>> getAllByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(reviewService.getAllReviewsForCourse(courseId));
    }
}
