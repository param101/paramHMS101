package com.fort.emr;

import com.fort.emr.bean.EMRPlanTreatmentLabsBean;
import com.fort.emr.bean.EMRTableBean;

public class EMRPlanTreatmentLabsAction extends EMRBaseAction {
	private String labCode;
	private String name;
	
	@Override
	protected boolean validateBean() {
		return true;
	}

	@Override
	protected EMRTableBean getBean() {
		return new EMRPlanTreatmentLabsBean(encounterId, row, actionType, labCode, name);
	}

	@Override
	public String toString() {
		return String.format("EMRPlanTreatmentLabsAction [labCode=%s, name=%s, row=%s, encounterId=%s, actionType=%s, isLocked=%s, user=%s]", labCode, name, row, encounterId, actionType, isLocked, user);
	}

	public String getLabCode() {
		return labCode;
	}

	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}