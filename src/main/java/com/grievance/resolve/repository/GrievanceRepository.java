package com.grievance.resolve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grievance.resolve.entity.Grievance;


public interface GrievanceRepository extends JpaRepository<Grievance, Long>{
	
		List<Grievance> findByUserId(Long userId);
	
}
