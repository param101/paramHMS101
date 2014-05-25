package com.fort.emr;

import java.sql.Date;

import com.fort.emr.bean.EMRPlanImmunizationsBean;
import com.fort.emr.bean.EMRTableBean;

public class EMRPlanImmunizationsAction extends EMRBaseAction {

	private Date dateGiven; 
	private String name;
	private String lotNr;
	private Date expDate;
	private String dose;
	private String route;
	private String location;
	private String givenBy;
	
	@Override
	protected boolean validateBean() {
		return true;
	}

	@Override
	protected EMRTableBean getBean() {
		return new EMRPlanImmunizationsBean(encounterId, row, actionType, dateGiven, name, lotNr, expDate, dose, route, location, givenBy);
	}

	@Override
	public String toString() {
		return String.format("EMRPlanImmunizationsAction [dateGiven=%s, name=%s, lotNr=%s, expDate=%s, dose=%s, route=%s, location=%s, givenBy=%s, row=%s, encounterId=%s, actionType=%s, isLocked=%s, user=%s]", dateGiven, name, lotNr, expDate, dose,
				route, location, givenBy, row, encounterId, actionType, isLocked, user);
	}

	public Date getDateGiven() {
		return dateGiven;
	}

	public void setDateGiven(Date dateGiven) {
		this.dateGiven = dateGiven;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLotNr() {
		return lotNr;
	}

	public void setLotNr(String lotNr) {
		this.lotNr = lotNr;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getGivenBy() {
		return givenBy;
	}

	public void setGivenBy(String givenBy) {
		this.givenBy = givenBy;
	}
}