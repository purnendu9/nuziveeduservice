package com.nuziveedu.service.dto;


public class NuziveeduFormDTO {
	  private Long  formID;
	  private String userID;
	  private String formData;
	public Long getFormID() {
		return formID;
	}
	public void setFormID(Long formID) {
		this.formID = formID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getFormData() {
		return formData;
	}
	public void setFormData(String formData) {
		this.formData = formData;
	}

}
