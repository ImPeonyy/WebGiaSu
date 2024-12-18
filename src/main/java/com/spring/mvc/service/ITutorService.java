package com.spring.mvc.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.spring.mvc.dto.TutorDTO;

public interface ITutorService {
	List<TutorDTO> findAll(Pageable pageable);
	List<TutorDTO> findAll();
	List<TutorDTO> searchTutorsByTerm(String searchTerm);
	int getTotalItem();
	TutorDTO findById(long id);
	TutorDTO findByUserId(long userId);
	TutorDTO save(TutorDTO dto);
	void delete(long[] ids);
	Map<Long, String> getAll();
}
