package com.spring.mvc.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.mvc.entity.RoleEntity;


public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	Optional<RoleEntity> findByCode(String code);
}
