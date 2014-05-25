package com.fort.emr;

import com.fort.emr.bean.EMRPlanPreventiveBean;
import com.fort.emr.bean.EMRTableBean;

public class EMRPlanPreventiveAction extends EMRBaseAction {

	private String diagnosis;
	private String description;
	
	@Override
	protected boolean validateBean() {
		return true;
	}

	@Override
	protected EMRTableBean getBean() {
		return new EMRPlanPreventiveBean(encounterId, row, description, diagnosis, description);
	}

	@Override
	public String toString() {
		return String.format("EMRPlanPreventiveAction [diagnosis=%s, description=%s, row=%s, encounterId=%s, actionType=%s, isLocked=%s, user=%s]", diagnosis, description, row, encounterId, actionType, isLocked, user);
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}