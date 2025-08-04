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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cart") 
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cart 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@OneToOne
    @JoinColumn(name = "learner_id", nullable = false, unique = true)
    private Learner learner;

	@OneToMany(mappedBy = "cart" , cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<CartItem> items = new ArrayList<>();
	
	
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "cart_courses",
        joinColumns = @JoinColumn(name = "cart_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();
	
	
	 public Cart(List<Course> courses, Learner learner,List<CartItem> items) {
		 this.courses = courses;
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
