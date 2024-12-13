package com.spring.mvc.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tutor")
public class TutorEntity extends BaseEntity  {
	@Column(name = "specialization")
	private String specialization;

	@Column(name = "education")
	private String education;

	@Column(name = "experience", columnDefinition = "TEXT")
	private String experience;

	@Column(name = "bio", columnDefinition = "TEXT")
	private String bio;
	
	@Column(name = "hourly_rate", columnDefinition = "TEXT")
	private String hourlyRate;
	
	@Column(name = "available_times", columnDefinition = "TEXT")
	private String availableTimes;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
	
	@OneToMany(mappedBy = "tutor")
	private List<RateEntity> rates = new ArrayList<>();

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(String hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public String getAvailableTimes() {
		return availableTimes;
	}

	public void setAvailableTimes(String availableTimes) {
		this.availableTimes = availableTimes;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public List<RateEntity> getRates() {
		return rates;
	}

	public void setRates(List<RateEntity> rates) {
		this.rates = rates;
	}


	
	
}
