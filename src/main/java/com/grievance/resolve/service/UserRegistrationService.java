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

    private final Map<String, UserRegistration> tempUsers = new HashMap<>();

    public String register(UserRegistrationDto dto) {

    	if (userRegistrationRepository.existsByEmail(dto.getEmail())) {
            return "Email already exists";
        }
        if (userRegistrationRepository.existsByPhone(dto.getPhone())) {
            return "Phone already exists";
        }

        UserRegistration userRegistration = userRegistrationMapper.toEntity(dto);
        userRegistration.setPassword(passwordEncoder.encode(dto.getPassword()));

        String otp = otpService.generateOtp(dto.getEmail());
        emailService.sendOtpEmail(dto.getEmail(), otp);

        tempUsers.put(dto.getEmail(), userRegistration);

        return "OTP sent successfully to " + dto.getEmail();
    }


    public UserResponseDto verifyOtp(OtpVerificationDto dto) {

    	if (!otpService.validateOtp(dto.getEmail(), dto.getOtp())) {
            throw new RuntimeException("Invalid or expired OTP!");
        }

        UserRegistration user = tempUsers.get(dto.getEmail());
        if (user == null) {
            throw new RuntimeException("User not found or already verified!");
        }

        String baseUsername = user.getFirstName().toLowerCase() + "." + user.getLastName().toLowerCase();
        String username = generateUniqueUsername(baseUsername);

        user.setUsername(username);

        userRegistrationRepository.save(user);

        tempUsers.remove(dto.getEmail());

        return userRegistrationMapper.toResponse(user, "Registration successful. Username generated.");
    }


    private String generateUniqueUsername(String base) {
        String username = base;
        Random random = new Random();
        int attempt = 0;

        while (userRegistrationRepository.existsByUsername(username)) {
            username = base + random.nextInt(1000);
            attempt++;
            if (attempt > 10) break;
        }

        return username;
    }
}
