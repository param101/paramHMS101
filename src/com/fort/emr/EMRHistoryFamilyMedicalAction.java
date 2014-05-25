package com.fort.emr;

import com.fort.emr.bean.EMRHistoryFamilyMedicalBean;
import com.fort.emr.bean.EMRTableBean;

public class EMRHistoryFamilyMedicalAction extends EMRBaseAction {

	private String relationship;
	private String status;
	private String condition;
	private String description;

	@Override
	protected boolean validateBean() {
		return true;
	}

	@Override
	protected EMRTableBean getBean() {
		return new EMRHistoryFamilyMedicalBean(encounterId, row, condition, relationship, status, condition, description);
	}

	@Override
	public String toString() {
		return String.format("EMRHistoryFamilyMedicalAction [relationship=%s, status=%s, condition=%s, description=%s, row=%s, encounterId=%s, actionType=%s, isLocked=%s, user=%s]", relationship, status, condition, description, row, encounterId,
				actionType, isLocked, user);
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
