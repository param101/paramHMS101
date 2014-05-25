package com.fort.emr.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class EMRHistoryFamilyMedicalBean extends EMRTableBean {
	private String relationship;
	private String status;
	private String condition;
	private String description;

	public EMRHistoryFamilyMedicalBean(Integer encounterId, Integer row, String actionType, String relationship, String status, String condition, String description) {
		super(encounterId, row, actionType);
		this.relationship = relationship;
		this.status = status;
		this.condition = condition;
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
		return new QueryParams("insert into emr_history_family (encounter_id, row, relationship, status, condition_, description, create_u, create_t ) values (?, ?, ?, ?, ?, ?, ?, now())", 
				new Object[]{getEncounterId(), getRow(), getRelationship(), getStatus(), getCondition(), getDescription(), updateUser	});
	}

	@Override
	public QueryParams updateQuery(String updateUser) {
		return new QueryParams( "update emr_history_family set relationship=?, status=?, condition_=?, description=?, update_u=?, update_t=now() where encounter_id=? and row=?",
				new Object[]{getRelationship(), getStatus(), getCondition(), getDescription(), updateUser, getEncounterId(), getRow()});
	}

	@Override
	public QueryParams refreshQuery() {
		return new QueryParams( "select encounter_id, row, relationship, status, condition_, description from emr_history_family where encounter_id=? ",
				new Object[]{getEncounterId()});
	}

	@Override
	public String getTableName() {
		return "emr_history_family";
	}

	@Override
	public EMRTableBean getBeanFromResultSet(ResultSet rs) throws SQLException {
		return new EMRHistoryFamilyMedicalBean(rs.getInt("encounter_id"), rs.getInt("row"), actionType, rs.getString("relationship"), rs.getString("status"), rs.getString("condition_"), rs.getString("description"));
	}

	@Override
	public void addDataToJson(JSONObject json) throws JSONException {
		json.put("relationship", getRelationship());
		json.put("status", getStatus());
		json.put("condition", getCondition());
		json.put("description", getDescription());
	}

	@Override
	public String toString() {
		return String.format("EMRHistoryFamilyMedicalBean [relationship=%s, status=%s, condition=%s, description=%s, encounterId=%s, row=%s, actionType=%s]", 
				relationship, status, condition, description, encounterId, row, actionType);
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
