package com.learn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.entities.Cart;
import com.learn.entities.Learner;

public interface CartRepository extends JpaRepository<Cart, Long> {

	Optional<Cart> findByLearnerId(Long learnerId);

}
