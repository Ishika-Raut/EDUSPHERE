package com.learn.service;


public interface OtpService {
	
	public String generateOtp(String email);
	
	public boolean validateOtp(String email,String otp);
	
	public void clearOtp(String email);
}
