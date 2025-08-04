package com.learn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CartItemDTO {
	private Long courseId;
	private String courseTitle;
	private double coursePrice;
	
}
