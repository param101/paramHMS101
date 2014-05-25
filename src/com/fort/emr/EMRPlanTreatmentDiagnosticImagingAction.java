package com.fort.emr;

import com.fort.emr.bean.EMRPlanTreatmentDiagnosticImagingBean;
import com.fort.emr.bean.EMRTableBean;

public class EMRPlanTreatmentDiagnosticImagingAction extends EMRBaseAction {

	private String labCode;
	private String type;
	private String name;
	
	@Override
	protected boolean validateBean() {
		return true;
	}

	@Override
	protected EMRTableBean getBean() {
		return new EMRPlanTreatmentDiagnosticImagingBean(encounterId, row, actionType, labCode, type, name);
	}

	@Override
	public String toString() {
		return String.format("EMRPlanTreatmentDiagnosticImagingAction [labCode=%s, type=%s, name=%s, row=%s, encounterId=%s, actionType=%s, isLocked=%s, user=%s]", labCode, type, name, row, encounterId, actionType, isLocked, user);
	}

	public String getLabCode() {
		return labCode;
	}

	public void setLabCode(String labCode) {
		this.labCode = labCode;
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
}