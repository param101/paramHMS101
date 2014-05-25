package com.fort.emr;

import java.sql.Date;

import com.fort.emr.bean.EMRHistoryMedicalBean;
import com.fort.emr.bean.EMRTableBean;

public class EMRHistoryMedicalAction extends EMRBaseAction {

	private Date date;
	private String icdCode;
	private String condition;
	private String description;

	@Override
	protected boolean validateBean() {
		return true;
	}

	@Override
	protected EMRTableBean getBean() {
		return new EMRHistoryMedicalBean(encounterId, row, actionType, date, icdCode, condition, description);
	}

	@Override
	public String toString() {
		return String.format("EMRHistoryMedicalAction [date=%s, icdCode=%s, condition=%s, description=%s, row=%s, encounterId=%s, actionType=%s, isLocked=%s, user=%s]", date, icdCode, condition, description, row, encounterId, actionType, isLocked,
				user);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIcdCode() {
		return icdCode;
	}

	public void setIcdCode(String icdCode) {
		this.icdCode = icdCode;
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