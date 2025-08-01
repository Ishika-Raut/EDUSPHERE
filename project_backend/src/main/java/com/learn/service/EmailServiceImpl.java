package com.learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {

	private final JavaMailSender mailSender;
	
	@Autowired
	public EmailServiceImpl(JavaMailSender mailSender) {
		super();
		this.mailSender = mailSender;
	}


	@Override
	public void sendOtpEmail(String to, String otp) {
		// TODO Auto-generated method stub
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject("Your OTP for Login");
		message.setText("Your OTP is: " + otp);
		//message.setFrom("");
		mailSender.send(message);
	}
}
