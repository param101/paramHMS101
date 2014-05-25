package com.fort.emr;

import com.fort.emr.bean.EMRHistoryAllergiesBean;
import com.fort.emr.bean.EMRTableBean;

public class EMRHistoryAllergiesAction extends EMRBaseAction {

	private String type;
	private String name;
	private String symptoms;
	
	@Override
	protected boolean validateBean() {
		return true;
	}

	@Override
	protected EMRTableBean getBean() {
		return new EMRHistoryAllergiesBean(encounterId, row, actionType, type, name, symptoms);
	}

	@Override
	public String toString() {
		return String.format("EMRHistoryAllergiesAction [type=%s, name=%s, symptoms=%s, row=%s, encounterId=%s, actionType=%s, isLocked=%s, user=%s]", type, name, symptoms, row, encounterId, actionType, isLocked, user);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
}
