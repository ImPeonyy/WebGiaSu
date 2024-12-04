package com.spring.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.mvc.entity.NewEntity;

public interface NewRepository extends JpaRepository<NewEntity, Long> {
	
}
