package com.fort.emr.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class EMRObjectivePhysicalExamBean extends EMRTableBean{

	private String title;
	private String symptomsAndDescriptions;
		
	public EMRObjectivePhysicalExamBean(Integer encounterId, Integer row, String actionType, String title, String symptomsAndDescriptions) {
		super(encounterId, row, actionType);
		this.title = title;
		this.symptomsAndDescriptions = symptomsAndDescriptions;
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
		return new QueryParams("insert into emr_objective_physicalexam (encounter_id, row, title, symptomsAndDescriptions, create_u, create_t) values(?, ?, ?, ?, ?, now())", 
				new Object[]{getEncounterId(), getRow(), getTitle(), getSymptomsAndDescriptions(), updateUser});
	}

	@Override
	public QueryParams updateQuery(String updateUser) {
		return new QueryParams("update emr_objective_physicalExam set title=?, symptomsAndDescriptions=?, update_u=?, update_t=now() where encounter_id=? and row=?", 
				new Object[]{getTitle(), getSymptomsAndDescriptions(), updateUser, getEncounterId(), getRow()});
	}

	@Override
	public QueryParams refreshQuery() {
		return new QueryParams("select encounter_id, row, title, symptomsAndDescriptions from emr_objective_physicalexam where encounter_id=?", 
				new Object[]{getEncounterId()});
	}

	@Override
	public String getTableName() {
		return "emr_objective_physicalexam";
	}

	@Override
	public EMRTableBean getBeanFromResultSet(ResultSet rs) throws SQLException {
		return new EMRObjectivePhysicalExamBean(rs.getInt("encounter_id"), rs.getInt("row"), actionType, rs.getString("title"), rs.getString("symptomsAndDescriptions"));
	}

	@Override
	public void addDataToJson(JSONObject json) throws JSONException {
		json.put("title", getTitle());
		json.put("symptomsAndDescriptions", getSymptomsAndDescriptions());
	}

	@Override
	public String toString() {
		return String.format("EMRObjectivePhysicalExamBean [title=%s, symptomsAndDescriptions=%s, encounterId=%s, row=%s, actionType=%s]", title, symptomsAndDescriptions, encounterId, row, actionType);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSymptomsAndDescriptions() {
		return symptomsAndDescriptions;
	}

	public void setSymptomsAndDescriptions(String symptomsAndDescriptions) {
		this.symptomsAndDescriptions = symptomsAndDescriptions;
	}
}
