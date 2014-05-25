package com.fort.emr;

import com.fort.emr.bean.EMRBillingCodeBean;
import com.fort.emr.bean.EMRTableBean;

public class EMRBillingCodeAction extends EMRBaseAction {

	private String icdCode;
	private String diagnosis;
	private String description;
	
	@Override
	protected boolean validateBean() {
		return true;
	}

	@Override
	protected EMRTableBean getBean() {
		return new EMRBillingCodeBean(encounterId, row, actionType, icdCode, diagnosis, description);
	}

	@Override
	public String toString() {
		return String.format("EMRBillingCodeAction [icdCode=%s, diagnosis=%s, description=%s, row=%s, encounterId=%s, actionType=%s, isLocked=%s, user=%s]", icdCode, diagnosis, description, row, encounterId, actionType, isLocked, user);
	}

	public String getIcdCode() {
		return icdCode;
	}

	public void setIcdCode(String icdCode) {
		this.icdCode = icdCode;
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