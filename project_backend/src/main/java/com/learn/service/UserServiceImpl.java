package com.learn.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learn.dto.OtpResponseDTO;
import com.learn.dto.OtpVerifyDTO;
import com.learn.dto.UserLoginDTO;
import com.learn.dto.UserRegisterDTO;
import com.learn.dto.UserResponseDTO;
import com.learn.dto.UserUpdateDTO;
import com.learn.dto.UserUpdateResponseDTO;
import com.learn.entities.Instructor;
import com.learn.entities.Learner;
import com.learn.entities.User;
import com.learn.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final ModelMapper modelMapper;
	
	private final OtpService otpService;
	
	private final EmailService emailService;

	@Override
	public UserResponseDTO registerUser(UserRegisterDTO dto) {
		
		if(userRepository.findByEmail(dto.getEmail()).isPresent()) {
			throw new RuntimeException("Email Already Registered!!!");	
		}
		
		User user = modelMapper.map(dto, User.class);		
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setEmailVerified(false);
		
		switch(dto.getRole().toUpperCase()) {
			case "LEARNER" -> {
				Learner learner = new Learner();
				learner.setUser(user);
				user.setLearner(learner);
			}
			
			case "INSTRUCTOR" -> {
				Instructor instructor = new Instructor();
				instructor.setUser(user);
				user.setInstructor(instructor);
			}
			
			case "ADMIN" -> {
				
			}
			
			default -> throw new RuntimeException("Invalid role: " + dto.getRole());
		}
		
		User savedUser = userRepository.save(user);
		
		return modelMapper.map(savedUser, UserResponseDTO.class);
		
	}

	@Override
	public OtpResponseDTO loginWithOtp(UserLoginDTO dto) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(dto.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid credentials");
		}
		
		String otp = otpService.generateOtp(user.getEmail());
		emailService.sendOtpEmail(user.getEmail(), otp);
		
		OtpResponseDTO response = modelMapper.map(user, OtpResponseDTO.class);
		response.setMessage("OTP sent to email");
		return response;
	}

	@Override
	public UserResponseDTO verifyOtp(OtpVerifyDTO dto) {
		// TODO Auto-generated method stub
		if(!otpService.validateOtp(dto.getEmail(), dto.getOtp())) {
			throw new RuntimeException("Invalid or expired OTP");
		}
		
		User user = userRepository.findByEmail(dto.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		user.setEmailVerified(true);
		userRepository.save(user);
		
		otpService.clearOtp(dto.getEmail());
		return mapToDTO(user);
	}

	@Override
	public UserResponseDTO mapToDTO(User user) {
		// TODO Auto-generated method stub
		return modelMapper.map(user, UserResponseDTO.class);
	}

	@Override
	public List<UserResponseDTO> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll().stream()
				.map(user -> modelMapper.map(user, UserResponseDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public UserResponseDTO getUserById(Long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + id));
		return mapToDTO(user);
	}

	@Override
	public UserUpdateResponseDTO updateUser(Long id, UserUpdateDTO dto) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		modelMapper.map(dto, user);
		userRepository.save(user);
		return modelMapper.map(user, UserUpdateResponseDTO.class);
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found"));
		userRepository.delete(user);
	}
	
	
}
