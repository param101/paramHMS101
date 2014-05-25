package com.fort.emr;

import com.fort.emr.bean.EMRTableBean;

public class EMRReviewOfSystemAction extends EMRBaseAction {

	private String system;
	private String description;

	@Override
	protected EMRTableBean getBean() {
		return new EMRReviewOfSystemBean(encounterId, row, actionType, system, description);
	}

	@Override
	public String toString() {
		return String.format("EMRReviewOfSystemAction [system=%s, description=%s, row=%s, encounterId=%s, actionType=%s, isLocked=%s, user=%s]", system, description, row, encounterId, actionType, isLocked, user);
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	protected boolean validateBean() {
		return true;
	}
}
