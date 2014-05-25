package com.fort.emr.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class EMRHistoryPsychiatricBean extends EMRTableBean {
	private String diagnosis;
	private String description;

	public EMRHistoryPsychiatricBean(Integer encounterId, Integer row, String actionType, String diagnosis, String description) {
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
		return new QueryParams("insert into emr_history_psychiatric (encounter_id, row, diagnosis, description, create_u, create_t) values(?, ?, ?, ?, ?, now()) ",
				new Object[]{getEncounterId(), getRow(), getDiagnosis(), getDescription(), updateUser});
	}

	@Override
	public QueryParams updateQuery(String updateUser) {
		return new QueryParams( "update emr_history_psychiatric set diagnosis=?, description=? where encounter_id=? and row=?",
				new Object[]{getDiagnosis(), getDescription(), getEncounterId(), getRow()});
	}

	@Override
	public QueryParams refreshQuery() {
		return new QueryParams( "select encounter_id, row, diagnosis, description from emr_history_psychiatric where encounter_id=?", new Object[]{getEncounterId()});
	}

	@Override
	public String getTableName() {
		return "emr_history_psychiatric";
	}

	@Override
	public EMRTableBean getBeanFromResultSet(ResultSet rs) throws SQLException {
		return new EMRHistoryPsychiatricBean(rs.getInt("encounter_id"), rs.getInt("row"), null, rs.getString("diagnosis"), rs.getString("description"));
	}

	@Override
	public void addDataToJson(JSONObject json) throws JSONException {
		json.put("diagnosis", getDiagnosis());
		json.put("description", getDescription());
	}

	@Override
	public String toString() {
		return String.format("EMRHistoryPsychiatricBean [diagnosis=%s, description=%s, encounterId=%s, row=%s, actionType=%s]", diagnosis, description, encounterId, row, actionType);
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