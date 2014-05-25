package com.fort.emr.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class EMRHpiBean extends EMRTableBean {

	private final String title;
	private final String symptoms;
	private final String description;

	public EMRHpiBean(Integer encounterId, Integer row, String actionType, String title, String symptoms, String description) {
		super(encounterId, row, actionType);
		this.actionType = actionType;
		this.title = title;
		this.symptoms = symptoms;
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
		return new QueryParams( "insert into emr_hpi (encounter_id, row, title, symptoms, description, create_t, create_u) values(?,?,?,?,?,now(),?)",
				new Object[]{getEncounterId(), getRow(), getTitle(), getSymptoms(), getDescription(), updateUser });
	}

	@Override
	public QueryParams updateQuery(String updateUser) {
		return new QueryParams( "update emr_hpi set title=?, symptoms=?, description=?, update_t=now(), update_u=? where row=? and encounter_id=?",
				new Object[]{getTitle(), getSymptoms(), getDescription(), updateUser, getRow(), getEncounterId()});
	}

	@Override
	public String getTableName() {
		return "emr_hpi";
	}

	@Override
	public QueryParams refreshQuery() {
		return new QueryParams( "select encounter_id, row, title, symptoms, description from emr_hpi where encounter_id=?",
		new Object[]{getEncounterId()} );
	}

	@Override
	public EMRTableBean getBeanFromResultSet(ResultSet rs) throws SQLException {
		return new EMRHpiBean(rs.getInt("encounter_id"), rs.getInt("row"), null, rs.getString("title"), rs.getString("symptoms"), rs.getString("description"));
	}

	@Override
	public void addDataToJson(JSONObject json) throws JSONException {
		json.put("title", title);
		json.put("symptoms", symptoms);
		json.put("description", description);
	}

	@Override
	public String toString() {
		return String.format("EMRHpiBean [title=%s, symptoms=%s, description=%s, encounterId=%s, row=%s, actionType=%s]", title, symptoms, description, encounterId, row, actionType);
	}

	public String getTitle() {
		return title;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public String getDescription() {
		return description;
	}
}