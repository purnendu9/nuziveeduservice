package com.nuziveedu.jdo.domain;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class NuziveeduForm {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.SEQUENCE)
	private Long formID;

	@Persistent
	private String userID;

	@Persistent
	private String formData;

	public NuziveeduForm(String userID, String formData) {
		super();
		this.userID = userID;
		this.formData = formData;
	}

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
