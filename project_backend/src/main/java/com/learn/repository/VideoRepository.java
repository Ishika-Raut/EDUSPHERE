package com.learn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.entities.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {

	Optional<Video> findByVideoUrl(String videoUrl);


}
