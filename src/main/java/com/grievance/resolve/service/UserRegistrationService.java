package com.grievance.resolve.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.grievance.resolve.dto.OtpVerificationDto;
import com.grievance.resolve.dto.UserRegistrationDto;
import com.grievance.resolve.dto.UserResponseDto;
import com.grievance.resolve.entity.UserRegistration;
import com.grievance.resolve.mapper.UserRegistrationMapper;
import com.grievance.resolve.repository.UserRegistrationRepository;

@Service
public class UserRegistrationService {
	
	@Autowired
	private UserRegistrationRepository userRegistrationRepository;
	
	@Autowired
	private UserRegistrationMapper userRegistrationMapper;
	
	@Autowired
	private OtpService otpService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private final Map<String, UserRegistration> tempUsers=new HashMap<>();
	
	public String register(UserRegistrationDto dto) {
		if(userRegistrationRepository.existsByEmail(dto.getEmail())) {
			return "Email already exist";
		}
		if(userRegistrationRepository.existsByPhone(dto.getPhone())) {
			return "Phone already exist";
		}
		
		UserRegistration userRegistration=userRegistrationMapper.toEntity(dto);
		userRegistration.setPassword(passwordEncoder.encode(dto.getPassword()));
		String otp=otpService.generateOtp(dto.getEmail());
		emailService.sendOtpEmail(dto.getEmail(), otp);
		
		tempUsers.put(dto.getEmail(), userRegistration);
		return "OTP sent to "+dto.getEmail();
	}
	
	public UserResponseDto verifyOtp(OtpVerificationDto dto) {
		if(otpService.validateOtp(dto.getEmail(), dto.getOtp())) {
			UserRegistration user=tempUsers.get(dto.getEmail());
			
			String username=user.getFirstName().toLowerCase()+"."+
							user.getLastName().toLowerCase()+
							(new Random().nextInt(1000));
			user.setUsername(username);
			userRegistrationRepository.save(user);
			
			return userRegistrationMapper.toResponse(user, "Registration Successful");
		}
		throw new RuntimeException("Invalid OTP!");
	}
	
	
	
}
