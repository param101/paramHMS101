package com.fort.emr;

import com.fort.emr.bean.EMRObjectivePhysicalExamBean;
import com.fort.emr.bean.EMRTableBean;

public class EMRObjectivePhysicalExamAction extends EMRBaseAction {
	
	private String title;
	private String symptomsAndDescriptions;
	
	@Override
	protected boolean validateBean() {
		return true;
	}

	@Override
	protected EMRTableBean getBean() {
		return new EMRObjectivePhysicalExamBean(encounterId, row, symptomsAndDescriptions, title, symptomsAndDescriptions);
	}

	@Override
	public String toString() {
		return String.format("EMRObjectivePhysicalExamAction [title=%s, symptomsAndDescriptions=%s, row=%s, encounterId=%s, actionType=%s, isLocked=%s, user=%s]", title, symptomsAndDescriptions, row, encounterId, actionType, isLocked, user);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSymptomsAndDescriptions() {
		return symptomsAndDescriptions;
	}

	public void setSymptomsAndDescriptions(String symptomsAndDescriptions) {
		this.symptomsAndDescriptions = symptomsAndDescriptions;
	}
}