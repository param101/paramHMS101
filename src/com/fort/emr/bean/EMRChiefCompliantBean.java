package com.fort.emr.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class EMRChiefCompliantBean extends EMRTableBean {
	private String description;
	private String actionType;

	public EMRChiefCompliantBean(Integer encounterId, Integer row, String description, String actionType) {
		super(encounterId, row, actionType);
		this.description = description;
		this.actionType = actionType;
	}

	@Override
	public String toString() {
		return String.format("EMRChiefCompliantBean [description=%s, actionType=%s, encounterId=%s, row=%s]", description, actionType, encounterId, row);
	}

	public String getDescription() {
		return description;
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
	public QueryParams insertQuery( String updateUser ) {
		return new QueryParams( "insert into emr_chief_complaint (encounter_id, row, description, create_t, create_u) values(?, ?, ?, now(), ? )", 
				new Object[]{getEncounterId(), getRow(), getDescription(), updateUser});
	}

	@Override
	public QueryParams updateQuery( String updateUser ) {
		return new QueryParams( "update emr_chief_complaint set description=?, update_u=?, update_t=now() where encounter_id=? and row=? ", 
				new Object[]{ getDescription(), updateUser, getRow(), getEncounterId()}); 
	}

	@Override
	public String getTableName() {
		return "emr_chief_complaint";
	}

	@Override
	public QueryParams refreshQuery() {
		return new QueryParams( "select encounter_id, row, description from emr_chief_complaint where encounter_id=?", 
				new Object[]{ getEncounterId()});
	}

	@Override
	public EMRTableBean getBeanFromResultSet(ResultSet rs) throws SQLException {
		return new EMRChiefCompliantBean(rs.getInt("encounter_id"), rs.getInt("row"), rs.getString("description"), null);
	}

	@Override
	public void addDataToJson(JSONObject json) throws JSONException {
		json.put("description", description);
	}
}