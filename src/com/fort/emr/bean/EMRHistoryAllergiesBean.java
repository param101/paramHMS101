package com.fort.emr.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class EMRHistoryAllergiesBean extends EMRTableBean {
	
	private String type;
	private String name;
	private String symptoms;
	
	public EMRHistoryAllergiesBean(Integer encounterId, Integer row, String actionType, String type, String name, String symptoms) {
		super(encounterId, row, actionType);
		this.type = type;
		this.name = name;
		this.symptoms = symptoms;
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
		return new QueryParams( "insert into emr_history_allergies( encounter_id, row, type, name, symptoms, create_u, create_t ) values( ?, ?, ?, ?, ?, ?, now() )",
				new Object[]{getEncounterId(), getRow(), getType(), getName(), getSymptoms(), updateUser });
	}

	@Override
	public QueryParams updateQuery(String updateUser) {
		return new QueryParams( "update emr_history_allergies set type=?, name=?, symptoms=?, update_u=?, update_t=now() where encounter_id=? and row=?",
				new Object[]{getType(), getName(), getSymptoms(), updateUser, getEncounterId(), getRow()});
	}

	@Override
	public QueryParams refreshQuery() {
		return new QueryParams("select encounter_id, row, type, name, symptoms from emr_history_allergies where encounter_id=?",
				new Object[]{ getEncounterId()});
	}

	@Override
	public String getTableName() {
		return "emr_history_allergies";
	}

	@Override
	public EMRTableBean getBeanFromResultSet(ResultSet rs) throws SQLException {
		return new EMRHistoryAllergiesBean(rs.getInt("encounter_id"), rs.getInt("row"), null, rs.getString("type"), rs.getString("name"), rs.getString("symptoms"));
	}

	@Override
	public void addDataToJson(JSONObject json) throws JSONException {
		json.put("type", getType());
		json.put("name", getName());
		json.put("symptoms", getSymptoms());
	}

	@Override
	public String toString() {
		return String.format("EMRHistoryAllergiesBean [type=%s, name=%s, symptoms=%s, encounterId=%s, row=%s, actionType=%s]", 
				type, name, symptoms, encounterId, row, actionType);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
}