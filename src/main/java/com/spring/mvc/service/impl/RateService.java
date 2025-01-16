package com.spring.mvc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mvc.converter.RateConverter;
import com.spring.mvc.dto.RateDTO;
import com.spring.mvc.entity.RateEntity;
import com.spring.mvc.entity.TutorEntity;
import com.spring.mvc.entity.UserEntity;
import com.spring.mvc.repository.RateRepository;
import com.spring.mvc.repository.TutorRepository;
import com.spring.mvc.repository.UserRepository;
import com.spring.mvc.service.IRateService;
import com.spring.mvc.util.SecurityUtils;

@Service
public class RateService implements IRateService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RateRepository rateRepository;
	
	@Autowired
	private TutorRepository tutorRepository;
	
	@Autowired
	private RateConverter rateConverter;

	@Override
	public List<RateDTO> findAllByTutorId(long tutorId) {
		List<RateDTO> models = new ArrayList<>();
		List<RateEntity> entities = rateRepository.findAllByTutor_Id(tutorId);
		for (RateEntity item: entities) {
			RateDTO rateDTO = rateConverter.toDto(item);
			models.add(rateDTO);
		}
		return models;
	}
	
	@Override
	@Transactional
	public RateDTO save(RateDTO dto) {
		UserEntity user = userRepository.findOne(SecurityUtils.getPrincipal().getId());
		TutorEntity tutor = tutorRepository.findById(dto.getTutorID());
		RateEntity rateEntity = new RateEntity();
			rateEntity = rateConverter.toEntity(dto);
			rateEntity.setUser(user);
			rateEntity.setTutor(tutor);

		return rateConverter.toDto(rateRepository.save(rateEntity));
	}

	
}
