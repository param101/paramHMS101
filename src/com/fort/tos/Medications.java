package com.fort.tos;

import java.sql.Date;
import java.sql.Time;

public class Medications {
	
     private Time encounter_dt;
	 private String name	  ;
	 private  String strength;
	 private String formulation;
	 private String route;
	 private String frequency;
	 private Date start_date;
	 private Date end_date;
	 private String condition_treated;
	 
	 
	
	public Medications(Time encounter_dt, String name, String strength,
			String formulation, String route, String frequency,
			Date start_date, Date end_date, String condition_treated) {
		super();
		this.encounter_dt = encounter_dt;
		this.name = name;
		this.strength = strength;
		this.formulation = formulation;
		this.route = route;
		this.frequency = frequency;
		this.start_date = start_date;
		this.end_date = end_date;
		this.condition_treated = condition_treated;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}

	public Time getEncounter_dt() {
		return encounter_dt;
	}
	public void setEncounter_dt(Time encounter_dt) {
		this.encounter_dt = encounter_dt;
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
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getCondition_treated() {
		return condition_treated;
	}
	public void setCondition_treated(String condition_treated) {
		this.condition_treated = condition_treated;
	}
	@Override
	public String toString() {
		return String
				.format("Medications [encounter_dt=%s, name=%s, strength=%s, formulation=%s, frequency=%s, start_date=%s, end_date=%s, condition_treated=%s]",
						encounter_dt, name, strength, formulation, frequency,
						start_date, end_date, condition_treated);
	}
	 
	

	
}
