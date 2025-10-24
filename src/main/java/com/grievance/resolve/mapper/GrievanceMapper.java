package com.grievance.resolve.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.grievance.resolve.dto.GrievanceDto;
import com.grievance.resolve.entity.Grievance;

@Mapper(componentModel = "spring")
public interface GrievanceMapper {
	
	Grievance toEntity(GrievanceDto dto);
	
	GrievanceDto toDto(Grievance grievance);
	
	List<GrievanceDto> toList(List<Grievance> grievances);

}
