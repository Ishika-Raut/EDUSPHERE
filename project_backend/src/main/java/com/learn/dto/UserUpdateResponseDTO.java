package com.learn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor
public class UserUpdateResponseDTO 
{
	private Long id;
	private String fullName;
	private String email;
	private String password;
	
}
