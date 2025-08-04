package com.learn.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import javax.management.RuntimeErrorException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.learn.entities.*;
import com.learn.dto.CartDTO;
import com.learn.dto.CartItemDTO;
import com.learn.repository.CartItemRepository;

import com.learn.repository.CartRepository;
import com.learn.repository.CourseRepository;
import com.learn.repository.LearnerRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CartServiceImpl implements CartService {

	private final CartRepository cartRepository;
	
	private final LearnerRepository learnerRepository;
	

	//private final CartItemRepository cartItemRepository;
	
	private final CourseRepository courseRepository;
	
	private final ModelMapper modelMapper;

	@Override

	public CartDTO addCourseToCart(Long learnerId, Long courseId) {

		// TODO Auto-generated method stub
		Learner learner = learnerRepository.findById(learnerId)
				.orElseThrow(() -> new RuntimeException("Learner not found"));
		
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new RuntimeException("Couse not found"));
		
		Cart cart = cartRepository.findByLearnerId(learnerId)
				.orElse(new Cart(null,learner,new ArrayList<>()));
		
		Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getCourse().getId().equals(courseId))
                .findFirst();

        if (existingItem.isPresent()) {
            throw new RuntimeException("Course already exists in cart");
        }

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setCourse(course);

        cart.getItems().add(cartItem);
        updateCartTotalPrice(cart);

        Cart savedCart = cartRepository.save(cart);
        return convertToDTO(savedCart);
	}

	@Override

	public CartDTO removeCourseFromCart(Long learnerId, Long courseId) {

		// TODO Auto-generated method stub
		Cart cart = cartRepository.findByLearnerId(learnerId)
				.orElseThrow(() -> new RuntimeException("Cart not found"));
		
		List<CartItem> updatedItems = cart.getItems().stream()
                .filter(item -> !item.getCourse().getId().equals(courseId))
                .collect(Collectors.toList());

        cart.getItems().clear();
        cart.getItems().addAll(updatedItems);

        updateCartTotalPrice(cart);
        Cart updatedCart = cartRepository.save(cart);
        return convertToDTO(updatedCart);
	}

	@Override
	public CartDTO getCartByLearner(Long learnerId) {

		// TODO Auto-generated method stub
		Cart cart = cartRepository.findByLearnerId(learnerId)
				.orElseThrow(() -> new RuntimeException("Cart not found"));
		
		updateCartTotalPrice(cart);
		return convertToDTO(cart);
	}


	private void updateCartTotalPrice(Cart cart) {

		double total = cart.getItems().stream()
                .mapToDouble(item -> item.getCourse().getPrice())
                .sum();
        cart.setTotalPrice(total);
	}

	
	
	private CartDTO convertToDTO(Cart cart) {

		return new CartDTO(
				cart.getLearner().getId(),null,
				cart.getItems().stream().map(item ->new CartItemDTO(
						item.getCourse().getId(),
						item.getCourse().getTitle(),
                        item.getCourse().getPrice()
                        )).collect(Collectors.toList()),
				cart.getTotalPrice()
			);
	}

	
	/*
	 @Override
    public void checkoutCart(Long learnerId) {
        Cart cart = getCartByLearnerId(learnerId);

        if (cart.getCourses().isEmpty()) {
            throw new RuntimeException("Cart is empty. Cannot checkout.");
        }

        // TODO: Business logic for payment and enrollment

        // After successful checkout, clear the cart
        cart.getCourses().clear();
        cartRepository.save(cart);
    }
    */
	

}
