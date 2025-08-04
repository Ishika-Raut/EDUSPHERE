package com.learn.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartDTO {

	private Long id;
	private Long learnerId;
	private List<CartItemDTO> items = new ArrayList<>();
	private Double totalPrice;
	

}

