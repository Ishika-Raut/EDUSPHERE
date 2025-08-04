package com.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.learn.entities.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    // Add custom queries if needed
}

