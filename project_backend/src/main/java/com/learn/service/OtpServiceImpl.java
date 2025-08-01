package com.learn.service;

import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class OtpServiceImpl implements OtpService {
	private final Map<String,String> otpStorage = new ConcurrentHashMap<>();
	private final Random random = new SecureRandom();
	@Override
	public String generateOtp(String email) {
		// TODO Auto-generated method stub
		String otp = String.format("%06d", random.nextInt(999999));
		otpStorage.put(email, otp);
		return otp;
	}

	@Override
	public boolean validateOtp(String email, String otp) {
		// TODO Auto-generated method stub
		return otp.equals(otpStorage.get(email));
	}

	@Override
	public void clearOtp(String email) {
		// TODO Auto-generated method stub
		otpStorage.remove(email);
	}

}
