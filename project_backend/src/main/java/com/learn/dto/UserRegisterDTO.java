package com.learn.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
	@NotBlank(message = "Full Name is required")
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
    
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
