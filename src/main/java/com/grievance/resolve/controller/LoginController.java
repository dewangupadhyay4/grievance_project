package com.grievance.resolve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grievance.resolve.dto.LoginRequestDto;
import com.grievance.resolve.dto.OtpLoginDto;
import com.grievance.resolve.service.LoginService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login/password")
	public String loginPassword(@Valid @RequestBody LoginRequestDto dto) {
		return loginService.loginUserVerify(dto.getUsername(), dto.getPassword());
	}
	
	@PostMapping("/login/verify-otp")
	public String verifyOtp(@RequestBody OtpLoginDto dto) {
		return loginService.verifyOtp(dto.getUsername(), dto.getOtp());
	}

}
