package com.fort.emr;

import com.fort.emr.bean.EMRHistorySocialBean;
import com.fort.emr.bean.EMRTableBean;

public class EMRHistorySocialAction extends EMRBaseAction {

	private String name;
	private String description;
	
	@Override
	protected boolean validateBean() {
		return true;
	}

	@Override
	protected EMRTableBean getBean() {
		return new EMRHistorySocialBean(encounterId, row, actionType, name, description);
	}

	@Override
	public String toString() {
		return String.format("EMRHistorySocialAction [name=%s, description=%s, row=%s, encounterId=%s, actionType=%s, isLocked=%s, user=%s]", name, description, row, encounterId, actionType, isLocked, user);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
