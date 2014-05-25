package com.fort.emr;

import com.fort.emr.bean.EMRHpiBean;
import com.fort.emr.bean.EMRTableBean;

public class EMRHpiAction extends EMRBaseAction {

	private String title;
	private String symptoms;
	private String description;
	
	@Override
	public String toString() {
		return String.format("EMRHpiAction [title=%s, symptoms=%s, description=%s, row=%s, encounterId=%s, actionType=%s, isLocked=%s, user=%s]", title, symptoms, description, row, encounterId, actionType, isLocked, user);
	}

	@Override
	protected boolean validateBean() {
		return true;
	}

	@Override
	protected EMRTableBean getBean() {
		return new EMRHpiBean(encounterId, row, description, title, symptoms, description);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
