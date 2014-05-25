package com.fort.emr.bean;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class EMRPlanTreatmentMedicationsBean extends EMRTableBean {

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
	
	
	public EMRPlanTreatmentMedicationsBean(Integer encounterId, Integer row, String actionType, String name, String strength, String formulation, String take, String route, String frequency, String duration, String quantity, String refills, Date startDate, Date endDate, String condition) {
		super(encounterId, row, actionType);
		this.name = name;
		this.strength = strength;
		this.formulation = formulation;
		this.take = take;
		this.route = route;
		this.frequency = frequency;
		this.duration = duration;
		this.quantity = quantity;
		this.refills = refills;
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
		return new QueryParams("insert into emr_plan_treat_med (encounter_id,row,name,strength,formulation,take,route,frequency,duration,quantity,refills,start_date,end_date,cond,create_u,create_t) " +
				" values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now())", new Object[]{getEncounterId(), getRow(), getName(), getStrength(),getFormulation(),getTake(),getRoute(),getFrequency(),getDuration(),getQuantity(),getRefills(),getStartDate(),getEndDate(), getCondition(), updateUser});
	}

	@Override
	public QueryParams updateQuery(String updateUser) {
		return new QueryParams("update emr_plan_treat_med set name=?,strength=?,formulation=?,take=?,route=?,frequency=?,duration=?,quantity=?,refills=?,start_date=?,end_date=?,cond=?, update_u=?, update_t=now() where encounter_id=? and row=?",
				new Object[]{getName(), getStrength(),getFormulation(),getTake(),getRoute(),getFrequency(),getDuration(),getQuantity(),getRefills(),getStartDate(),getEndDate(), getCondition(), updateUser, getEncounterId(), getRow()});

	}

	@Override
	public QueryParams refreshQuery() {
		return new QueryParams("select encounter_id, row, name,strength,formulation,take,route,frequency,duration,quantity,refills,start_date,end_date,cond from emr_plan_treat_med where encounter_id=?" , new Object[]{getEncounterId()});
	}

	@Override
	public String getTableName() {
		return "emr_plan_treat_med";
	}

	@Override
	public EMRTableBean getBeanFromResultSet(ResultSet rs) throws SQLException {
		return new EMRPlanTreatmentMedicationsBean(rs.getInt("encounter_id"), rs.getInt("row"), actionType, rs.getString("name"), rs.getString("strength"),rs.getString( "formulation" ),rs.getString( "take" ),rs.getString( "route" ),rs.getString( "frequency" ),rs.getString( "duration" ),rs.getString( "quantity" ),rs.getString( "refills" ),rs.getDate( "start_date" ),rs.getDate( "end_date" ),rs.getString( "cond" ));
	}

	@Override
	public void addDataToJson(JSONObject json) throws JSONException {
		json.put("name", getName());
		json.put("strength", getStrength());
		json.put("formulation", getFormulation());
		json.put("take", getTake());
		json.put("route", getRoute());
		json.put("frequency", getFrequency());
		json.put("duration", getDuration());
		json.put("quantity", getQuantity());
		json.put("refills", getRefills());
		json.put("startDate", getStartDate()==null?"NA":getStartDate());
		json.put("endDate", getEndDate()==null?"NA":getEndDate());
		json.put("condition", getCondition());
	}

	@Override
	public String toString() {
		return String.format(
				"EMRPlanTreatmentMedicationsBean [name=%s, strength=%s, formulation=%s, take=%s, route=%s, frequency=%s, duration=%s, quantity=%s, refills=%s, startDate=%s, endDate=%s, condition=%s, encounterId=%s, row=%s, actionType=%s]", name,
				strength, formulation, take, route, frequency, duration, quantity, refills, startDate, endDate, condition, encounterId, row, actionType);
	}

	public String getName() {
		return name;
	}

	public String getStrength() {
		return strength;
	}

	public String getFormulation() {
		return formulation;
	}

	public String getTake() {
		return take;
	}

	public String getRoute() {
		return route;
	}

	public String getFrequency() {
		return frequency;
	}

	public String getDuration() {
		return duration;
	}

	public String getQuantity() {
		return quantity;
	}

	public String getRefills() {
		return refills;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getCondition() {
		return condition;
	}
}
