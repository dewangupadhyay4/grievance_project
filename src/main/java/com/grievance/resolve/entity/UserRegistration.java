
package com.grievance.resolve.entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_registration")
public class UserRegistration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY	)
	private Long id;
	
	private String firstName;
	private String middleName;
	private String lastName;
	private LocalDate dob;
	private long phone;
	private String email;
	private String address;
	private String otp;
	
	@Column(name="username", unique = true)
	private String username;
	private String password;
	private boolean verified;
	
	

}
