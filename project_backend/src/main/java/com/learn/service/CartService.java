package com.learn.service;

import com.learn.dto.CartDTO;

public interface CartService {
	CartDTO addCourseToCart(Long learnerId, Long courseId);

	CartDTO removeCourseFromCart(Long learnerId, Long courseId);

	CartDTO getCartByLearner(Long learnerId);

	
	//void clearCart(Long learnerId);
	
    //void checkoutCart(Long learnerId);
    
    //Cart createCartForLearner(Long learnerId);
	

}
