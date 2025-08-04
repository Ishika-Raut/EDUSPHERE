package com.learn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.learn.entities.JobSuggestion;

public interface JobSuggestionRepository extends JpaRepository<JobSuggestion, Long> {
	@Query("SELECT j FROM JobSuggestion j WHERE LOWER(j.jobTitle) LIKE LOWER(CONCAT('%',:courseTitle, '%'))")
	List<JobSuggestion> findByCourseTag(@Param("courseTitle") String courseTitle);
}
