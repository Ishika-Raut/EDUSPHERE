package com.learn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.entities.Learner;
import com.learn.entities.Video;
import com.learn.entities.VideoProgress;

public interface VideoProgressRepository extends JpaRepository<VideoProgress, Long> 
{
    Optional<VideoProgress> findByLearnerAndVideo(Learner learner, Video video);
    
    List<VideoProgress> findByLearner(Learner learner);
}

