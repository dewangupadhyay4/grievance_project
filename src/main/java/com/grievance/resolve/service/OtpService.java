package com.grievance.resolve.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class OtpService {
	
	private final Map<String, String> otpStorage=new HashMap<>();
	
	public String generateOtp(String email) {
		String otp=String.valueOf(new Random().nextInt(900000)+100000);
		otpStorage.put(email, otp);
		return otp;
	}
	
	public boolean validateOtp(String email, String otp) {
		return otp.equals(otpStorage.get(email));
	}

}
