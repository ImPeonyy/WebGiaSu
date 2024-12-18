package com.spring.mvc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.mvc.entity.TutorEntity;

public interface TutorRepository extends JpaRepository<TutorEntity, Long> {
	 Optional<TutorEntity> findByUser_Id(Long userId);
	 TutorEntity findById(Long id);
	 
	 @Query("SELECT t FROM TutorEntity t JOIN t.user u WHERE " +
		       "(LOWER(u.fullName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR :searchTerm IS NULL) " +
		       "OR (LOWER(t.specialization) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR :searchTerm IS NULL) " +
		       "OR (LOWER(t.education) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR :searchTerm IS NULL) " +
		       "OR (LOWER(t.experience) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR :searchTerm IS NULL) " +
		       "OR (LOWER(t.hourlyRate) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR :searchTerm IS NULL) " +
		       "OR (LOWER(t.availableTimes) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR :searchTerm IS NULL)")
	 List<TutorEntity> searchTutors(@Param("searchTerm") String searchTerm);
}
