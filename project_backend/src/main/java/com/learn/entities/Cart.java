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
    

	 @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	 @JoinColumn(name = "cart_id")
	 private List<CartItem> items = new ArrayList<>();
	 
	 public Cart(Learner learner,List<CartItem> items) {
		 this.learner = learner;
		 this.items = items;
	 }

	 public void addItem(CartItem item) {
		 this.items.add(item);
	 }
	 
	 public void removeItem(CartItem item) {
		 this.items.remove(item);
	 }
	 
	 public double getTotalPrice() {
		 return items.stream()
				 .mapToDouble(ci -> ci.getCourse().getPrice())
				 .sum();
	 }

	 public void setTotalPrice(double total) {
		// TODO Auto-generated method stub
		
	 }
}
