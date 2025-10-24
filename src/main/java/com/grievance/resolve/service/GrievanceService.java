package com.grievance.resolve.service;

import java.util.List;

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
		grievanceRepository.save(grievance);
		return grievanceMapper.toDto(grievance);
	}
	
	public List<GrievanceDto> getUserGrievance(String username){
		List<Grievance> list=grievanceRepository.findByUsername(username);
		List<GrievanceDto> grievanceDtos=grievanceMapper.toList(list);
		return grievanceDtos;
	}
	
}
