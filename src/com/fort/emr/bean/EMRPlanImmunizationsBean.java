package com.fort.emr.bean;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class EMRPlanImmunizationsBean extends EMRTableBean {
	private Date dateGiven; 
	private String name;
	private String lotNr;
	private Date expDate;
	private String dose;
	private String route;
	private String location;
	private String givenBy;
	
	public EMRPlanImmunizationsBean(Integer encounterId, Integer row, String actionType, Date dateGiven, String name, String lotNr, Date expDate, String dose, String route, String location, String givenBy) {
		super(encounterId, row, actionType);
		this.dateGiven = dateGiven;
		this.name = name;
		this.lotNr = lotNr;
		this.expDate = expDate;
		this.dose = dose;
		this.route = route;
		this.location = location;
		this.givenBy = givenBy;
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
		return new QueryParams("insert into emr_plan_immunizations (encounter_id, row, dateGiven, name, lotNr, expDate, dose, route, location, givenBy, create_u, create_t ) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now())", 
				new Object[]{getEncounterId(), getRow(), getDateGiven(), getName(), getLotNr(), getExpDate(), getDose(), getRoute(), getLocation(), getGivenBy(), updateUser});
	}

	@Override
	public QueryParams updateQuery(String updateUser) {
		return new QueryParams("update emr_plan_immunizations set dateGiven=?, name=?, lotNr=?, expDate=?, dose=?, route=?, location=?, givenBy=?, update_u=?, update_t=now() where encounter_id=? and row=?", 
				new Object[]{getDateGiven(), getName(), getLotNr(), getExpDate(), getDose(), getRoute(), getLocation(), getGivenBy(), updateUser, getEncounterId(), getRow()});
	}

	@Override
	public QueryParams refreshQuery() {
		return new QueryParams("select encounter_id, row, dateGiven, name, lotNr, expDate, dose, route, location, givenBy from emr_plan_immunizations where encounter_id=?", 
				new Object[]{getEncounterId()});
	}

	@Override
	public String getTableName() {
		return "emr_plan_immunizations";
	}

	@Override
	public EMRTableBean getBeanFromResultSet(ResultSet rs) throws SQLException {
		return new EMRPlanImmunizationsBean(rs.getInt("encounter_id"), rs.getInt("row"), actionType, rs.getDate("dateGiven"), rs.getString("name"), rs.getString("lotNr"), rs.getDate("expDate"), rs.getString("dose"), rs.getString("route"), rs.getString("location"), rs.getString("givenBy"));
	}

	@Override
	public void addDataToJson(JSONObject json) throws JSONException {
		json.put("dateGiven", getDateGiven());
		json.put("name", getName());
		json.put("lotNr", getLotNr());
		json.put("expDate", getExpDate());
		json.put("dose", getDose());
		json.put("route", getRoute());
		json.put("location", getLocation());
		json.put("givenBy", getGivenBy());
	}

	@Override
	public String toString() {
		return String.format("EMRPlanImmunizationsBean [dateGiven=%s, name=%s, lotNr=%s, expDate=%s, dose=%s, route=%s, location=%s, givenBy=%s, encounterId=%s, row=%s, actionType=%s]", dateGiven, name, lotNr, expDate, dose, route, location,
				givenBy, encounterId, row, actionType);
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