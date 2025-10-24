package com.grievance.resolve.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrievanceDto {

	private Long id;
	private String username;
	private String title;
	private String description;
	private Double lattitude;
	private Double Longitude;
	private String department;
	private String status="pending";
	private LocalDateTime createdAt=LocalDateTime.now();
	
}
