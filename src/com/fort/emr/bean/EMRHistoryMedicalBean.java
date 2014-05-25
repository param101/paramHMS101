package com.fort.emr.bean;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class EMRHistoryMedicalBean extends EMRTableBean {
	private Date date;
	private String icdCode;
	private String condition;
	private String description;
	
	public EMRHistoryMedicalBean(Integer encounterId, Integer row, String actionType, Date date, String icdCode, String condition, String description) {
		super(encounterId, row, actionType);
		this.date = date;
		this.icdCode = icdCode;
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
		return new QueryParams("insert into emr_history_medical (encounter_id, row, date, icd_code, condition_, description, create_u, create_t ) values (?, ?, ?, ?, ?, ?, ?, now()) ",
				new Object[]{getEncounterId(), getRow(), getDate(), getIcdCode(), getCondition(), getDescription(), updateUser });
	}

	@Override
	public QueryParams updateQuery(String updateUser) {
		return new QueryParams("update emr_history_medical set date=?, icd_code=?, condition_=?, description=? where encounter_id=? and row=?",
				new Object[]{getDate(), getIcdCode(), getCondition(), getDescription(), getEncounterId(), getRow()});
	}

	@Override
	public QueryParams refreshQuery() {
		return new QueryParams( "select encounter_id, row, date, concat( i.description, ' [', i.icd,']') as icd_code, condition_, e.description from emr_history_medical e, ICD_CODES i where e.icd_code=i.icd and encounter_id=?", 
				new Object[]{getEncounterId()});
	}

	@Override
	public String getTableName() {
		return "emr_history_medical";
	}

	@Override
	public EMRTableBean getBeanFromResultSet(ResultSet rs) throws SQLException {
		return new EMRHistoryMedicalBean(rs.getInt("encounter_id"), rs.getInt("row"), null, rs.getDate("date"), rs.getString("icd_code"), rs.getString("condition_"), rs.getString("description"));
	}

	@Override
	public void addDataToJson(JSONObject json) throws JSONException {
		json.put("date", getDate());
		json.put("icdCode", getIcdCode());
		json.put("condition", getCondition());
		json.put("description", getDescription());
	}

	@Override
	public String toString() {
		return String.format("EMRHistoryMedicalBean [date=%s, icdCode=%s, condition=%s, description=%s, encounterId=%s, row=%s, actionType=%s]", 
				date, icdCode, condition, description, encounterId, row, actionType);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIcdCode() {
		return icdCode;
	}

	public void setIcdCode(String icdCode) {
		this.icdCode = icdCode;
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