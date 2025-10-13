package com.grievance.resolve.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.grievance.resolve.dto.UserRegistrationDto;
import com.grievance.resolve.dto.UserResponseDto;
import com.grievance.resolve.entity.UserRegistration;

@Mapper(componentModel = "spring")
public interface UserRegistrationMapper {
	
	UserRegistration toEntity(UserRegistrationDto userRegistrationDto);
	
	@Mapping(target = "message", expression = "java(message)")
	UserResponseDto toResponse(UserRegistration userRegistration, String message);

}
