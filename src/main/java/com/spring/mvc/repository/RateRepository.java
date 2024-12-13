package com.spring.mvc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.mvc.entity.RateEntity;

public interface RateRepository extends JpaRepository<RateEntity, Long> {
	List<RateEntity> findAllByTutor_Id(Long tutorId);
}
