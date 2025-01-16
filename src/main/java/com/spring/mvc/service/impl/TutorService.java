package com.spring.mvc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mvc.converter.TutorConverter;
import com.spring.mvc.dto.TutorDTO;
import com.spring.mvc.entity.TutorEntity;
import com.spring.mvc.entity.UserEntity;
import com.spring.mvc.repository.TutorRepository;
import com.spring.mvc.repository.UserRepository;
import com.spring.mvc.service.ITutorService;
import com.spring.mvc.util.SecurityUtils;

@Service
public class TutorService implements ITutorService{
	@Autowired
	private TutorRepository tutorRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TutorConverter TutorConverter;
	
	@Override
	public List<TutorDTO> findAll(Pageable pageable) {
		List<TutorDTO> models = new ArrayList<>();
		List<TutorEntity> entities = tutorRepository.findAll(pageable).getContent();
		for (TutorEntity item: entities) {
			TutorDTO TutorDTO = TutorConverter.toDto(item);
			models.add(TutorDTO);
		}
		return models;
	}

	@Override
	public int getTotalItem() {
		return (int) tutorRepository.count();
	}

	@Override
	public TutorDTO findById(long id) {
		TutorEntity entity = tutorRepository.findOne(id);
		return TutorConverter.toDto(entity);
	}
	
	@Override
	@Transactional
	public TutorDTO save(TutorDTO dto) {
		UserEntity user = userRepository.findOne(SecurityUtils.getPrincipal().getId());
//		UserEntity user = userRepository.findOne(dto.getUserID());
		TutorEntity tutorEntity = new TutorEntity();
		if (dto.getId() != null) {
			TutorEntity oldTutor = tutorRepository.findOne(dto.getId());
			tutorEntity = TutorConverter.toEntity(oldTutor, dto);
		} else {
			tutorEntity = TutorConverter.toEntity(dto);
			tutorEntity.setUser(user);
		}
		return TutorConverter.toDto(tutorRepository.save(tutorEntity));
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for (long id: ids) {
			tutorRepository.delete(id);
		}
	}
	
	@Override
	public Map<Long, String> getAll() {
		Map<Long, String> result = new HashMap<>();
		List<UserEntity> entities = userRepository.findUsersByUserIdNotInTutor();
		for (UserEntity item: entities) {
			result.put(item.getId(), item.getFullName());
		}
		return result;
	}

	@Override
	public List<TutorDTO> findAll() {
		List<TutorDTO> models = new ArrayList<>();
		List<TutorEntity> entities = tutorRepository.findAll();
		for (TutorEntity item: entities) {
			TutorDTO TutorDTO = TutorConverter.toDto(item);
			models.add(TutorDTO);
		}
		return models;
	}

	@Override
	public TutorDTO findByUserId(long userId) {
		Optional<TutorEntity> entity = tutorRepository.findByUser_Id(userId);
		if (entity.isPresent()) {
	        return TutorConverter.toDto(entity.get());
	    } else {
	        return null;  // or throw an exception, or return a default DTO
	    }
	}

	@Override
	public List<TutorDTO> searchTutorsByTerm(String searchTerm) {
		List<TutorDTO> models = new ArrayList<>();
		List<TutorEntity> entities = tutorRepository.searchTutors(searchTerm);
		for (TutorEntity item: entities) {
			TutorDTO TutorDTO = TutorConverter.toDto(item);
			models.add(TutorDTO);
		}
		return models;
	}
}
