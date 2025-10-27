package com.grievance.resolve.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grievance.resolve.entity.Grievance;


public interface GrievanceRepository extends JpaRepository<Grievance, Long>{
	
		List<Grievance> findByUsername(String username);
		
		Optional<Grievance> findByTicketNumber(String ticket);
	
}
