package com.learn.service;

import java.util.List;

import com.learn.dto.OtpResponseDTO;
import com.learn.dto.OtpVerifyDTO;
import com.learn.dto.UserLoginDTO;
import com.learn.dto.UserRegisterDTO;
import com.learn.dto.UserResponseDTO;
import com.learn.dto.UserUpdateDTO;
import com.learn.dto.UserUpdateResponseDTO;
import com.learn.entities.User;

public interface UserService {

	UserResponseDTO registerUser(UserRegisterDTO dto);
	
	OtpResponseDTO loginWithOtp(UserLoginDTO dto);
	
	UserResponseDTO verifyOtp(OtpVerifyDTO dto);
	
	UserResponseDTO mapToDTO(User user);
	
	List<UserResponseDTO> getAllUsers();

	UserResponseDTO getUserById(Long id);

	UserUpdateResponseDTO updateUser(Long id, UserUpdateDTO dto);

	void deleteUser(Long id);
}
