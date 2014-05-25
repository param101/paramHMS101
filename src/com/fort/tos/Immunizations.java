package com.fort.tos;

import java.sql.Date;
import java.sql.Time;

public class Immunizations {
	
    private int pat_id; 
	private Time encounter_dt;
	private Date date_given;
	
	 private String name;
	 private  String lotNr;
	 private Date expDate;
	 private String dose;
	 private String route;
	 private String location;
	 private String givenBy;
	public Immunizations(int pat_id, Time encounter_dt, Date date_given,
			String name, String lotNr, Date expDate, String dose, String route,
			String location,String givenBy) {
		super();
		this.pat_id = pat_id;
		this.encounter_dt = encounter_dt;
		this.date_given = date_given;
		this.name = name;
		this.lotNr = lotNr;
		this.expDate = expDate;
		this.dose = dose;
		this.route = route;
		this.location = location;
	}
	public int getPat_id() {
		return pat_id;
	}
	public void setPat_id(int pat_id) {
		this.pat_id = pat_id;
	}
	public Time getEncounter_dt() {
		return encounter_dt;
	}
	public void setEncounter_dt(Time encounter_dt) {
		this.encounter_dt = encounter_dt;
	}
	public Date getDate_given() {
		return date_given;
	}
	public void setDate_given(Date date_given) {
		this.date_given = date_given;
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
	@Override
	public String toString() {
		return String
				.format("Immunizations [pat_id=%s, encounter_dt=%s, date_given=%s, name=%s, lotNr=%s, expDate=%s, dose=%s, route=%s, location=%s,givenBy=%s]",
						pat_id, encounter_dt, date_given, name, lotNr, expDate,
						dose, route, location,givenBy);
	}
}
