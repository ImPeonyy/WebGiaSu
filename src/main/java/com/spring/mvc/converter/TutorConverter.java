package com.spring.mvc.converter;

import org.springframework.stereotype.Component;
import com.spring.mvc.dto.TutorDTO;
import com.spring.mvc.entity.TutorEntity;

@Component
public class TutorConverter {
	public TutorDTO toDto(TutorEntity entity) {
		TutorDTO result = new TutorDTO();
		result.setId(entity.getId());
		result.setSpecialization(entity.getSpecialization());
		result.setEducation(entity.getEducation());
		result.setExperience(entity.getExperience());
		result.setBio(entity.getBio());
		result.setHourlyRate(entity.getHourlyRate());
		result.setAvailableTimes(entity.getAvailableTimes());
		result.setUserID(entity.getUser().getId());
		result.setFullName(entity.getUser().getFullName());
		return result;
	}
	
	public TutorEntity toEntity(TutorDTO dto) {
		TutorEntity result = new TutorEntity();
		result.setSpecialization(dto.getSpecialization());
		result.setEducation(dto.getEducation());
		result.setExperience(dto.getExperience());
		result.setBio(dto.getBio());
		result.setHourlyRate(dto.getHourlyRate());
		result.setAvailableTimes(dto.getAvailableTimes());
		return result;
	}
	
	public TutorEntity toEntity(TutorEntity result, TutorDTO dto) {
		result.setSpecialization(dto.getSpecialization());
		result.setEducation(dto.getEducation());
		result.setExperience(dto.getExperience());
		result.setBio(dto.getBio());
		result.setHourlyRate(dto.getHourlyRate());
		result.setAvailableTimes(dto.getAvailableTimes());
		return result;
	}
}
