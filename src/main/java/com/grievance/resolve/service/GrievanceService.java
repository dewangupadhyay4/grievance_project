package com.grievance.resolve.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grievance.resolve.dto.GrievanceDto;
import com.grievance.resolve.entity.Grievance;
import com.grievance.resolve.mapper.GrievanceMapper;
import com.grievance.resolve.repository.GrievanceRepository;

@Service
public class GrievanceService {

	@Autowired
	private GrievanceRepository grievanceRepository;
	
	@Autowired
	private GrievanceMapper grievanceMapper;
	
	
	public GrievanceDto createGrievance(GrievanceDto grievanceDto) {
		Grievance grievance=grievanceMapper.toEntity(grievanceDto);
		grievance.setStatus("pending");
		grievance.setTicketNumber(generateTicketNumber());
		grievanceRepository.save(grievance);
		return grievanceMapper.toDto(grievance);
	}
	
	public List<GrievanceDto> getUserGrievance(String username){
		List<Grievance> list=grievanceRepository.findByUsername(username);
		List<GrievanceDto> grievanceDtos=grievanceMapper.toList(list);
		return grievanceDtos;
	}
	
	private String generateTicketNumber() {
		String datePart=LocalDateTime.now()
				.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		String randomPart=UUID.randomUUID().toString().substring(0,6).toUpperCase();
		return "Tic"+datePart+randomPart;
	}
	
	public GrievanceDto getGrievanceByTicket(String ticket) {
		Grievance grievance=grievanceRepository.findByTicketNumber(ticket).orElseThrow(()->new RuntimeException("Ticket Not Found"));
		return grievanceMapper.toDto(grievance);
	}
	
}
