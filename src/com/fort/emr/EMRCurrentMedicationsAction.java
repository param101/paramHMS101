package com.fort.emr;

import java.sql.Date;

import com.fort.emr.bean.EMRCurrentMedicationsBean;
import com.fort.emr.bean.EMRTableBean;

public class EMRCurrentMedicationsAction extends EMRBaseAction {

	private String name;
	private String strength;
	private String formulation;
	private String route;
	private String frequency;
	private Date startDate;
	private Date endDate;
	private String condition;
	
	@Override
	protected boolean validateBean() {
		return true;
	}

	@Override
	protected EMRTableBean getBean() {
		return new EMRCurrentMedicationsBean(encounterId, row, actionType, name, strength, formulation, route, frequency, startDate, endDate, condition);
	}

	@Override
	public String toString() {
		return String.format("EMRCurrentMedicationsAction [name=%s, strength=%s, formulation=%s, route=%s, frequency=%s, startDate=%s, endDate=%s, condition=%s, row=%s, encounterId=%s, actionType=%s, isLocked=%s, user=%s]", name, strength,
				formulation, route, frequency, startDate, endDate, condition, row, encounterId, actionType, isLocked, user);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getFormulation() {
		return formulation;
	}

	public void setFormulation(String formulation) {
		this.formulation = formulation;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
}
