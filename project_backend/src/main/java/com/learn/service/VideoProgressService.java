package com.learn.service;

import com.learn.dto.VideoProgressDTO;

public interface VideoProgressService 
{

	VideoProgressDTO updateProgress(VideoProgressDTO dto);

	VideoProgressDTO getProgress(Long learnerId, Long videoId);

}
