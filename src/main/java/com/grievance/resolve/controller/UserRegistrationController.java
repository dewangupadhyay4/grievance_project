package com.grievance.resolve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grievance.resolve.dto.OtpVerificationDto;
import com.grievance.resolve.dto.UserRegistrationDto;
import com.grievance.resolve.dto.UserResponseDto;
import com.grievance.resolve.service.UserRegistrationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRegistrationController {
	
	@Autowired
	private UserRegistrationService userRegistrationService;

	@PostMapping("/register")
	public String register(@RequestBody UserRegistrationDto dto) {
		return userRegistrationService.register(dto);
	}
	
	@PostMapping("/verify")
	public UserResponseDto verifyOtp(@RequestBody OtpVerificationDto dto) {
		return userRegistrationService.verifyOtp(dto);
	}
	
	
}
