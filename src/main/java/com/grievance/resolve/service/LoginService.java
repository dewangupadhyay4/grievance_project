package com.grievance.resolve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.grievance.resolve.entity.UserRegistration;
import com.grievance.resolve.repository.UserRegistrationRepository;

@Service
public class LoginService {
	
	@Autowired
	private UserRegistrationRepository registrationRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private OtpService otpService;
	
	@Autowired
	private EmailService emailService;
	
	
	public String loginUserVerify(String username, String password) {
		UserRegistration user=registrationRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("User not found"));
		if(!passwordEncoder.matches(password, user.getPassword())) {
			return "Invalid password";
		}
		
		String otp=otpService.generateOtp(user.getEmail());
		emailService.sendOtpEmail(user.getEmail(), otp);
		return "OTP sent to registered mail";
	}
	
	public String verifyOtp(String username, String otp) {
		UserRegistration user=registrationRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("User not found"));
		if(otpService.validateOtp(user.getEmail(), otp)) {
			return "Login Successfull";
		}
		else {
			return "Invalid OTP!";
		}
	}

}
