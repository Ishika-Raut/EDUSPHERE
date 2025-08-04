package com.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.entities.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

}
