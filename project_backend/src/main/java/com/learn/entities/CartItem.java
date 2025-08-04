package com.learn.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "cart_items") 
@NoArgsConstructor
@AllArgsConstructor
public class CartItem
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false, unique = true)
    private Cart cart;
    
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false, unique = true)
    private Course course;
	    
}
