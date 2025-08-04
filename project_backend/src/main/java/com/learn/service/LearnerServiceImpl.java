package com.learn.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learn.dto.LearnerDTO;
import com.learn.entities.Learner;
import com.learn.repository.LearnerRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class LearnerServiceImpl implements LearnerService 
{

	 private final LearnerRepository learnerRepository;
	 private final ModelMapper modelMapper;

    @Override
    public List<LearnerDTO> getAllLearners() {
        List<Learner> learners = learnerRepository.findAll();
        return learners.stream()
                .map(learner -> modelMapper.map(learner, LearnerDTO.class))
                .collect(Collectors.toList());
    }
    
}

