package com.grievance.resolve.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grievance.resolve.dto.GrievanceDto;
import com.grievance.resolve.entity.UserLogin;
import com.grievance.resolve.entity.UserRegistration;
import com.grievance.resolve.repository.UserRegistrationRepository;
import com.grievance.resolve.service.GrievanceService;

@RequestMapping("/api/grievances")
@RestController
public class GrievanceController {
	
	@Autowired
	private GrievanceService grievanceService;
	
	@Autowired
	private UserRegistrationRepository userRegistrationRepository;
	
	@PostMapping("/raise")
	public ResponseEntity<GrievanceDto> raiseGrievance(@RequestBody GrievanceDto dto){
		
		UserRegistration user=userRegistrationRepository.findByUsername(dto.getUsername()).orElseThrow(()->new RuntimeException("User Not Found"));
		
		GrievanceDto saved=grievanceService.createGrievance(dto);
		return new ResponseEntity(saved, HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{username}")
	public ResponseEntity<List<GrievanceDto>> getUserGrievances(@PathVariable String username){
		List<GrievanceDto> list=grievanceService.getUserGrievance(username);
		return ResponseEntity.ok(list);
	}

}
