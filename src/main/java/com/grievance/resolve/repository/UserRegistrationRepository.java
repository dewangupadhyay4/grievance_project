package com.grievance.resolve.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grievance.resolve.entity.UserRegistration;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Long>{
	boolean existsByEmail(String email);
	boolean existsByPhone(long phone);
	Optional<UserRegistration> findByEmail(String email);
	Optional<UserRegistration> findByUsername(String username);
    boolean existsByUsername(String username);

	
}
