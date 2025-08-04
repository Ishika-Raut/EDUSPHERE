package com.learn.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learn.dto.VideoProgressDTO;
import com.learn.entities.Learner;
import com.learn.entities.Video;
import com.learn.entities.VideoProgress;
import com.learn.repository.LearnerRepository;
import com.learn.repository.VideoProgressRepository;
import com.learn.repository.VideoRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class VideoProgressServiceImpl implements VideoProgressService
{
	
    private final VideoProgressRepository videoProgressRepository;
    private final LearnerRepository learnerRepository;
    private final VideoRepository videoRepository;
    private final ModelMapper modelMapper;

    // Update or Create progress
    @Override
    public VideoProgressDTO updateProgress(VideoProgressDTO dto) 
    {
    	Learner learner = learnerRepository.findById(dto.getLearnerId())
                .orElseThrow(() -> new RuntimeException("Learner not found"));

        Video video = videoRepository.findById(dto.getVideoId())
                .orElseThrow(() -> new RuntimeException("Video not found"));

        VideoProgress progress = videoProgressRepository.findByLearnerAndVideo(learner, video)
                .orElse(new VideoProgress());

        progress.setLearner(learner);
        progress.setVideo(video);
        progress.setCompleted(dto.isCompleted());
        progress.setWatchedOn(dto.getWatchedOn() != null ? dto.getWatchedOn() : LocalDateTime.now());

        VideoProgress saved = videoProgressRepository.save(progress);

        return modelMapper.map(saved, VideoProgressDTO.class);

    }
    

    // Get progress by learnerId & videoId
    @Override
    public VideoProgressDTO getProgress(Long learnerId, Long videoId) 
    {
        Learner learner = learnerRepository.findById(learnerId)
                .orElseThrow(() -> new RuntimeException("Learner not found"));

        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found"));

        VideoProgress progress = videoProgressRepository.findByLearnerAndVideo(learner, video)
                .orElseThrow(() -> new RuntimeException("Progress not found"));

        return modelMapper.map(progress, VideoProgressDTO.class);
    }
}
