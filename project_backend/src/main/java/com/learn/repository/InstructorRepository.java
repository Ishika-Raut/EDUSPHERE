package com.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.entities.Instructor;


public interface InstructorRepository extends JpaRepository<Instructor, Long>
{
	//add a custom query to fetch instructor + course details -
	//in single left outer join query
	 //@Query("select i from Instructor i left join fetch i.createdCourses where i.id=:id");
	 //Optional<Instructor> fetchRestaurantAndMenu(/* @Param("id") */Long id);
	 
	//use inherited method - save
	//add a derived finder method to check if the name already exists
	//Optional<Instructor> findByName(String instructorName);
}
