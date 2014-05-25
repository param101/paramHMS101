package com.fort.emr.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class EMRPlanTreatmentDiagnosticImagingBean extends EMRTableBean {

	private String labCode;
	private String type;
	private String name;
	
	
	
	public EMRPlanTreatmentDiagnosticImagingBean(Integer encounterId, Integer row, String actionType, String labCode, String type, String name) {
		super(encounterId, row, actionType);
		this.labCode = labCode;
		this.type = type;
		this.name = name;
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
		return new QueryParams("insert into emr_plan_treat_diag_imag (encounter_id,row,lab_code, type, name, create_u,create_t) values(?, ?, ?, ?, ?, ?, now())",
				new Object[]{getEncounterId(), getRow(), getLabCode(), getType(), getName(), updateUser});
	}

	@Override
	public QueryParams updateQuery(String updateUser) {
		return new QueryParams("update emr_plan_treat_diag_imag set lab_code=?, type=?, name=?, update_u=?, update_t=now() where encounter_id=? and row=?",
				new Object[]{getLabCode(), getType(), getName(), updateUser, getEncounterId(), getRow()});
	}

	@Override
	public QueryParams refreshQuery() {
		return new QueryParams("select encounter_id, row, lab_code, type, name from emr_plan_treat_diag_imag where encounter_id=?",
				new Object[]{getEncounterId()});
	}

	@Override
	public String getTableName() {
		return "emr_plan_treat_diag_imag";
	}

	@Override
	public EMRTableBean getBeanFromResultSet(ResultSet rs) throws SQLException {
		return new EMRPlanTreatmentDiagnosticImagingBean(rs.getInt("encounter_id"), rs.getInt("row"), actionType, rs.getString("lab_code"), rs.getString("type"),rs.getString("name"));
	}

	@Override
	public void addDataToJson(JSONObject json) throws JSONException {
		json.put("labCode", getLabCode());
		json.put("type",getType());
		json.put("name", getName());
	}

	@Override
	public String toString() {
		return String.format("EMRPlanTreatmentDiagnosticImagingBean [labCode=%s, type=%s, name=%s, encounterId=%s, row=%s, actionType=%s]", labCode, type, name, encounterId, row, actionType);
	}

	public String getLabCode() {
		return labCode;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}
}
