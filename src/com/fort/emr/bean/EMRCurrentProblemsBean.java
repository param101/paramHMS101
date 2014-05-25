package com.fort.emr.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class EMRCurrentProblemsBean extends EMRTableBean {
	private String icdCode;
	private String diagnosis;
	private String description;

	public EMRCurrentProblemsBean(Integer encounterId, Integer row, String actionType, String icdCode, String diagnosis, String description) {
		super(encounterId, row, actionType);
		this.icdCode = icdCode;
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
		return new QueryParams("insert into emr_current_problems (encounter_id, row, icd_code, diagnosis, description, create_u, create_t) values(?, ?, ?, ?, ?, ?, now())",
				new Object[]{getEncounterId(), getRow(), getIcdCode(), getDiagnosis(), getDescription(), updateUser });
	}

	@Override
	public QueryParams updateQuery(String updateUser) {
		return new QueryParams( "update emr_current_problems set icd_code=?, diagnosis=?, description=?, update_u=?, update_t=now() where encounter_id=? and row=?",
				new Object[]{getIcdCode(), getDiagnosis(), getDescription(), updateUser, getEncounterId(), getRow()});
	}

	@Override
	public QueryParams refreshQuery() {
		return new QueryParams( "select encounter_id, row, concat( i.description, ' [', i.icd,']') as icd_code, diagnosis, e.description from emr_current_problems e, ICD_CODES i where e.icd_code=i.icd and encounter_id=?",
				new Object[]{ getEncounterId()});
	}

	@Override
	public String getTableName() {
		return "emr_current_problems";
	}

	@Override
	public EMRTableBean getBeanFromResultSet(ResultSet rs) throws SQLException {
		return new EMRCurrentProblemsBean(rs.getInt("encounter_id"), rs.getInt("row"), null, rs.getString("icd_code"), rs.getString("diagnosis"), rs.getString("description"));
	}

	@Override
	public void addDataToJson(JSONObject json) throws JSONException {
		json.put("icdCode", getIcdCode());
		json.put("diagnosis", getDiagnosis());
		json.put("description", getDescription());
	}

	@Override
	public String toString() {
		return String.format("EMRCurrentProblemsBean [icdCode=%s, diagnosis=%s, description=%s, encounterId=%s, row=%s, actionType=%s]", 
				icdCode, diagnosis, description, encounterId, row, actionType);
	}

	public String getIcdCode() {
		return icdCode;
	}

	public void setIcdCode(String icdCode) {
		this.icdCode = icdCode;
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