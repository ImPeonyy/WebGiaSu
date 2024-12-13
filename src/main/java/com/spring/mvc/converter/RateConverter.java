package com.spring.mvc.converter;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.spring.mvc.dto.RateDTO;
import com.spring.mvc.entity.RateEntity;

@Component
public class RateConverter {
	public RateDTO toDto(RateEntity entity) {
		Date createdDate = entity.getCreatedDate();
		Timestamp timestamp = new Timestamp(createdDate.getTime());
		RateDTO result = new RateDTO();
		result.setId(entity.getId());
		result.setRate(entity.getRate());
		result.setComment(entity.getComment());
		result.setTutorID(entity.getTutor().getId());
		result.setUserID(entity.getUser().getId());
		result.setCreatedBy(entity.getCreatedBy());
		result.setCreatedDate(timestamp);
		result.setFullName(entity.getUser().getFullName());
		return result;
	}
	
	public RateEntity toEntity(RateDTO dto) {
		RateEntity result = new RateEntity();
		result.setRate(dto.getRate());
		result.setComment(dto.getComment());
		return result;
	}
	
	public RateEntity toEntity(RateEntity result, RateDTO dto) {
		result.setRate(dto.getRate());
		result.setComment(dto.getComment());
		return result;
	}
}
