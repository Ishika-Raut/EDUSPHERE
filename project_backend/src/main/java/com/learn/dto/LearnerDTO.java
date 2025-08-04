package com.learn.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LearnerDTO 
{
    private Long id;
    private Long userId; // assuming User entity already exists
}
