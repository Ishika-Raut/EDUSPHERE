package com.learn.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor
@ToString
public class UserRegisterDTO 
{
	@NotBlank(message = "Full name is required")
    @Size(min = 3, max = 50, message = "Full name must be between 3 and 50 characters")
    private String fullName;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
    private String email;
	
	@NotBlank(message = "Password is required")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$"
	,message = "Password must be at least 8 characters, include uppercase, lowercase, digit, and special character")
    private String password;
	
	@NotBlank(message = "Role is required")
	@Pattern(regexp = "^(LEARNER|INSTRUCTOR|ADMIN)$",
	message = "Role must be one of: LEARNER, INSTRUCTOR, ADMIN")
    private String role; // STUDENT, INSTRUCTOR OR ADMIN
    
}
