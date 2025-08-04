package com.learn.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.dto.CartDTO;
import com.learn.service.CartService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartController {
	private final CartService cartService;

	
	@PostMapping("/{learnerId}/add/{courseId}")
	public ResponseEntity<CartDTO> addCourse(@PathVariable Long learnerId,@PathVariable Long courseId){
		return ResponseEntity.ok(cartService.addCourseToCart(learnerId,courseId));
	}
	
	@DeleteMapping("/{learnerId}/remove/{courseId}")
	public ResponseEntity<CartDTO> removeCourse(@PathVariable Long learnerId,@PathVariable Long courseId){
		return ResponseEntity.ok(cartService.removeCourseFromCart(learnerId,courseId));
	}
	
	@GetMapping("/{learnerId}")
	public ResponseEntity<CartDTO> viewCart(@PathVariable Long learnerId){
		return ResponseEntity.ok(cartService.getCartByLearner(learnerId));
	}
}
