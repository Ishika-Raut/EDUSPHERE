package com.learn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learn.entities.Course;
import com.learn.entities.Instructor;

public interface CourseRepository extends JpaRepository<Course, Long>{

	//add a custom query to fetch course + video details -
	//in single left outer join query
	 @Query("select c from Course c left join fetch c.videos where c.id=:id")
	 Optional<Instructor> fetchCourseAndVideo(/* @Param("id") */Long id);
	 
	Optional<Course> findByTitle(String courseName);

}
