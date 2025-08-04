package com.learn.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.dto.OtpResponseDTO;
import com.learn.dto.OtpVerifyDTO;
import com.learn.dto.UserLoginDTO;
import com.learn.dto.UserRegisterDTO;
import com.learn.dto.UserResponseDTO;
import com.learn.dto.UserUpdateDTO;
import com.learn.dto.UserUpdateResponseDTO;
import com.learn.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Validated
public class UserController {

	private final UserService userService;

	@PostMapping("/register")
	public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRegisterDTO dto){
		UserResponseDTO response = userService.registerUser(dto);
		return ResponseEntity.ok(response);
	}
	
	//Login with email and password and send otp
	@PostMapping("/login/firststep") 
	public ResponseEntity<OtpResponseDTO> loginFirstStep(@Valid @RequestBody UserLoginDTO dto){
		return ResponseEntity.ok(userService.loginWithOtp(dto));
	}
	
	//login with email and otp , return user + token
	@PostMapping("/login/secondstep") 
	public ResponseEntity<UserResponseDTO> loginSecondStep(@Valid @RequestBody OtpVerifyDTO dto){
		UserResponseDTO response = userService.verifyOtp(dto);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getallusers")
	public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@GetMapping("/getuserbyid/{id}")
	public ResponseEntity<UserResponseDTO> getUserById(@PathVariable @NotNull @Min(1) Long id){
		return ResponseEntity.ok(userService.getUserById(id));
	}
	
	@PutMapping("/updatebyid/{id}")
	public ResponseEntity<UserUpdateResponseDTO> updateUser(@PathVariable @NotNull @Min(1) Long id,@Valid @RequestBody UserUpdateDTO dto){
		return ResponseEntity.ok(userService.updateUser(id,dto));
	}
	
	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
		return ResponseEntity.ok("User deleted successfully");
	}
}
