package com.fort.emr;

import com.fort.emr.bean.EMRChiefCompliantBean;
import com.fort.emr.bean.EMRTableBean;

public class EMRChiefComplaintAction extends EMRBaseAction {
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	protected boolean validateBean() {
		logInfo("no validation required here");
		return true;
	}

	@Override
	protected EMRTableBean getBean() {
		return new EMRChiefCompliantBean(encounterId, row, description, actionType);
	}

	@Override
	public String toString() {
		return String.format("EMRChiefComplaintAction [description=%s, row=%s, encounterId=%s, actionType=%s, isLocked=%s, user=%s]", description, row, encounterId, actionType, isLocked, user);
	}
}