package com.learn.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "cart") 
@NoArgsConstructor
@AllArgsConstructor
public class Cart 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@OneToOne
	@JoinColumn(name="learner_id", nullable = false)
    private Learner learner;
    

	 @OneToMany(mappedBy = "cart" , cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	 private List<CartItem> cartItems = new ArrayList<>();
}
