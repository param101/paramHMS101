package com.fort.emr.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class EMRPlanPreventiveBean extends EMRTableBean {

	private String diagnosis;
	private String description;

	public EMRPlanPreventiveBean(Integer encounterId, Integer row, String actionType, String diagnosis, String description) {
		super(encounterId, row, actionType);
		this.diagnosis = diagnosis;
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
		return new QueryParams("insert into emr_plan_preventive (encounter_id, row, diagnosis, description, create_u, create_t ) values(?, ?, ?, ?, ?, now()) ", 
				new Object[]{getEncounterId(), getRow(), getDiagnosis(), getDescription(), updateUser });
	}

	@Override
	public QueryParams updateQuery(String updateUser) {
		return new QueryParams("update emr_plan_preventive set diagnosis=?, description=?, update_u=?, update_t=now() where encounter_id=? and row=?", 
				new Object[]{getDiagnosis(), getDescription(), updateUser, getEncounterId(), getRow()});
	}

	@Override
	public QueryParams refreshQuery() {
		return new QueryParams("select encounter_id, row, diagnosis, description from emr_plan_preventive where encounter_id=?", 
				new Object[]{getEncounterId()});
	}

	@Override
	public String getTableName() {
		return "emr_plan_preventive";
	}

	@Override
	public EMRTableBean getBeanFromResultSet(ResultSet rs) throws SQLException {
		return new EMRPlanPreventiveBean(rs.getInt("encounter_id"), rs.getInt("row"), null, rs.getString("diagnosis"), rs.getString("description"));
	}

	@Override
	public void addDataToJson(JSONObject json) throws JSONException {
		json.put("diagnosis", getDiagnosis());
		json.put("description", getDescription());
	}

	@Override
	public String toString() {
		return String.format("EMRPlanPreventiveBean [diagnosis=%s, description=%s, encounterId=%s, row=%s, actionType=%s]", diagnosis, description, encounterId, row, actionType);
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}