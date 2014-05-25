package com.fort.emr;

import java.sql.Date;

import com.fort.emr.bean.EMRPlanTreatmentMedicationsBean;
import com.fort.emr.bean.EMRTableBean;

public class EMRPlanTreatmentMedicationsAction extends EMRBaseAction {

	String name;
	String strength;
	String formulation;
	String take;
	String route;
	String frequency;
	String duration;
	String quantity;
	String refills;
	Date startDate;
	Date endDate;
	String condition;
	
	@Override
	protected boolean validateBean() {
		return true;
	}

	@Override
	protected EMRTableBean getBean() {
		return new EMRPlanTreatmentMedicationsBean(encounterId, row, actionType, name, strength, formulation, take, route, frequency, duration, quantity, refills, startDate, endDate, condition);
	}

	@Override
	public String toString() {
		return String
				.format("EMRPlanTreatmentMedicationsAction [name=%s, strength=%s, formulation=%s, take=%s, route=%s, frequency=%s, duration=%s, quantity=%s, refills=%s, startDate=%s, endDate=%s, condition=%s, row=%s, encounterId=%s, actionType=%s, isLocked=%s, user=%s]",
						name, strength, formulation, take, route, frequency, duration, quantity, refills, startDate, endDate, condition, row, encounterId, actionType, isLocked, user);
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

	public String getTake() {
		return take;
	}

	public void setTake(String take) {
		this.take = take;
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

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getRefills() {
		return refills;
	}

	public void setRefills(String refills) {
		this.refills = refills;
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
