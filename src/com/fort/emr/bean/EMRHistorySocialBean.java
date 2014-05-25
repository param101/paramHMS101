package com.fort.emr.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class EMRHistorySocialBean extends EMRTableBean {

	private String name;
	private String description;
	
	
	public EMRHistorySocialBean(Integer encounterId, Integer row, String actionType, String name, String description) {
		super(encounterId, row, actionType);
		this.name = name;
		this.description = description;
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
		return new QueryParams("insert into emr_history_social (encounter_id, row, name, description, create_u, create_t) values (?, ?, ?, ?, ?, now())",
				new Object[]{getEncounterId(), getRow(), getName(), getDescription(), updateUser});
	}

	@Override
	public QueryParams updateQuery(String updateUser) {
		return new QueryParams("update emr_history_social set name=?, description=?, update_u=?, update_t=now() where encounter_id=? and row=?",
				new Object[]{getName(), getDescription(), updateUser, getEncounterId(), getRow() });
	}

	@Override
	public QueryParams refreshQuery() {
		return new QueryParams("select encounter_id, row, name, description from emr_history_social where encounter_id=?", 
				new Object[]{getEncounterId()});
	}

	@Override
	public String getTableName() {
		return "emr_history_social";
	}

	@Override
	public EMRTableBean getBeanFromResultSet(ResultSet rs) throws SQLException {
		return new EMRHistorySocialBean(rs.getInt("encounter_id"), rs.getInt("row"), actionType, rs.getString("name"), rs.getString("description"));
	}

	@Override
	public void addDataToJson(JSONObject json) throws JSONException {
		json.put("name", getName());
		json.put("description", getDescription());
	}

	@Override
	public String toString() {
		return String.format("EMRHistorySocialBean [name=%s, description=%s, encounterId=%s, row=%s, actionType=%s]", name, description, encounterId, row, actionType);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}