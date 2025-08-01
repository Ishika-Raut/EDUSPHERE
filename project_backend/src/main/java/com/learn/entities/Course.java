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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "course") 
@NoArgsConstructor
@AllArgsConstructor
public class Course 
{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	    @NotBlank(message = "Course title is required")
	    @Size(min = 2, max = 100, message = "Course title must be between 2 and 100 characters")
	    private String title;

	    @NotBlank(message = "Course description is required")
	    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
	    private String description;

	    @PositiveOrZero(message = "Price must be zero or positive")
	    private double price;

	    @NotBlank(message = "Category is required")
	    private String category;

	    @NotBlank(message = "Thumbnail URL is required")
	    @Size(max = 500, message = "Thumbnail URL is too long")
	    private String thumbnailUrl;

	    @ManyToOne
	    @JoinColumn(name = "instructor_id", nullable = false)
	    @NotNull(message = "Instructor is required for a course")
	    private Instructor instructor;

	    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	    private List<Video> videos = new ArrayList<>();

	    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	    private List<CartItem> cartItems = new ArrayList<>();

	    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	    private List<Review> reviews = new ArrayList<>();
}

