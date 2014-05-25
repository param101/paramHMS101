package com.fort.emr.bean;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class EMRCurrentMedicationsBean extends EMRTableBean {

	private String name;
	private String strength;
	private String formulation;
	private String route;
	private String frequency;
	private Date startDate;
	private Date endDate;
	private String condition;

	public EMRCurrentMedicationsBean(Integer encounterId, Integer row, String actionType, String name, String strength, String formulation, String route, String frequency, Date startDate, Date endDate, String condition) {
		super(encounterId, row, actionType);
		this.name = name;
		this.strength = strength;
		this.formulation = formulation;
		this.route = route;
		this.frequency = frequency;
		this.startDate = startDate;
		this.endDate = endDate;
		this.condition = condition;
	}

	@Override
	public boolean isInsertSupported() {
		return true;
	}

	@Override
	public boolean isDeleteSupported() {
		return true;
	}

	@Override
	public boolean isUpdateSupported() {
		return true;
	}

	@Override
	public QueryParams insertQuery(String updateUser) {
		return new QueryParams("insert into emr_current_medications (encounter_id, row, name, strength, formulation, route, frequency, startdate, enddate, conditiontreated, create_u, create_t ) " +
				" values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now()) ",
				new Object[]{getEncounterId(), getRow(), getName(), getStrength(), getFormulation(), getRoute(), getFrequency(), getStartDate(), getEndDate(), getCondition(), updateUser });
	}

	@Override
	public QueryParams updateQuery(String updateUser) {
		return new QueryParams( "update emr_current_medications set name=?, strength=?, formulation=?, route=?, frequency=?, startdate=?, enddate=?, conditiontreated=?, update_u=?, update_t=now() where encounter_id=? and row=?", 
				new Object[]{getName(), getStrength(), getFormulation(), getRoute(), getFrequency(), getStartDate(), getEndDate(), getCondition(), updateUser, getEncounterId(), getRow() });
	}

	@Override
	public QueryParams refreshQuery() {
		return new QueryParams( "select encounter_id, row, name, strength, formulation, route, frequency, startdate, enddate, conditiontreated from emr_current_medications where encounter_id=?",
				new Object[]{ getEncounterId()});
	}

	@Override
	public String getTableName() {
		return "emr_current_medications";
	}

	@Override
	public EMRTableBean getBeanFromResultSet(ResultSet rs) throws SQLException {
		return new EMRCurrentMedicationsBean(rs.getInt("encounter_id"), rs.getInt("row"), null, rs.getString("name"), rs.getString("strength"), rs.getString("formulation"), rs.getString("route"), rs.getString("frequency"), rs.getDate("startdate"), rs.getDate("enddate"), rs.getString("conditiontreated"));
	}

	@Override
	public void addDataToJson(JSONObject json) throws JSONException {
		json.put("name", getName());
		json.put("strength", getStrength());
		json.put("formulation", getFormulation());
		json.put("route", getRoute());
		json.put("frequency", getFrequency());
		json.put("startDate", getStartDate());
		json.put("endDate", getEndDate());
		json.put("condition", getCondition());
	}

	@Override
	public String toString() {
		return String.format("EMRCurrentMedicationsBean [name=%s, strength=%s, formulation=%s, route=%s, frequency=%s, startDate=%s, endDate=%s, condition=%s, encounterId=%s, row=%s, actionType=%s]", 
				name, strength, formulation, route, frequency, startDate, endDate, condition, encounterId, row, actionType);
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