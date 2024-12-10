package com.spring.mvc.model;

public class RateModel extends AbstractModel<RateModel>{
	private Integer rate;
	private String comment;
	private Long tutorID;
	private Long userID;
	private String fullName;
	
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getTutorID() {
		return tutorID;
	}
	public void setTutorID(Long tutorID) {
		this.tutorID = tutorID;
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
