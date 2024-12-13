package com.spring.mvc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.mvc.entity.TutorEntity;

public interface TutorRepository extends JpaRepository<TutorEntity, Long> {
	 Optional<TutorEntity> findByUser_Id(Long userId);
	 TutorEntity findById(Long id);
	 Page<TutorEntity> findByUserFullNameContainingIgnoreCaseOrSpecializationContainingIgnoreCaseOrHourlyRateContainingIgnoreCaseOrEducationContainingIgnoreCase(Pageable pageable, String fn, String sp, String hr, String ed);
//	 @Query("SELECT t FROM TutorEntity t " +
//	           "JOIN t.user u " + // Join with User entity
//	           "WHERE LOWER(u.fullName) LIKE LOWER(CONCAT('%', :fullName, '%')) " +
//	           "OR LOWER(t.specialization) LIKE LOWER(CONCAT('%', :specialization, '%')) " +
//	           "OR LOWER(t.hourlyRate) LIKE LOWER(CONCAT('%', :hourlyRate, '%')) " +
//	           "OR LOWER(t.education) LIKE LOWER(CONCAT('%', :education, '%'))")
}
