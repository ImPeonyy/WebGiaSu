package com.spring.mvc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.mvc.entity.TutorEntity;

public interface TutorRepository extends JpaRepository<TutorEntity, Long> {
	 Optional<TutorEntity> findByUser_Id(Long userId);
}
