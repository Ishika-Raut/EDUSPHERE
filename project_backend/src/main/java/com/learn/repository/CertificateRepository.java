package com.learn.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.learn.entities.Certificate;
import com.learn.entities.Learner;

public interface CertificateRepository extends JpaRepository<Certificate, Long>{
	List<Certificate> findByLearner(Learner learner);

	List<Certificate> findByLearnerId(Long learnerId);
}
