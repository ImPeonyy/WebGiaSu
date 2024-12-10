package com.spring.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.mvc.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findOneByUserNameAndStatus(String name, int status);
	UserEntity findOneById(long id);
	boolean existsByUserName(String un);
	@Query("SELECT u FROM UserEntity u WHERE u.id NOT IN (SELECT t.user.id FROM TutorEntity t)")
	List<UserEntity> findUsersByUserIdNotInTutor();
}
