package com.spring.mvc.service;

import java.util.List;

import com.spring.mvc.dto.RateDTO;

public interface IRateService {
	List<RateDTO> findAllByTutorId(long userId);
	RateDTO save(RateDTO dto);
}
