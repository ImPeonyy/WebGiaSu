package com.spring.mvc.dto;

public class TutorDTO extends AbstractDTO<TutorDTO>{
	private String specialization;
	private String education;
	private String experience;
	private String bio;
	private String hourlyRate;
	private String availableTimes;
	private Long userID;
	private String fullName;
	
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
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
}
