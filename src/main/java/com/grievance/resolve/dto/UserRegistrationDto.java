package com.grievance.resolve.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserRegistrationDto {
	
	private String firstName;
	private String middleName;
	private String lastName;
	private LocalDate dob;
	private long phone;
	private String email;
	private String address;
	private String password;

}
