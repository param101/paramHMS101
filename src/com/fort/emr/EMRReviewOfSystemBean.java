package com.fort.emr;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.fort.emr.bean.EMRTableBean;

public class EMRReviewOfSystemBean extends EMRTableBean {
	private final String system;
	private final String description;
	
	public EMRReviewOfSystemBean(Integer encounterId, Integer row, String actionType, String system, String description) {
		super(encounterId, row, actionType);
		this.system = system;
		this.description = description;
	}

	@Override
	public String toString() {
		return String.format("EMRReviewOfSystemBean [system=%s, description=%s, encounterId=%s, row=%s, actionType=%s]", system, description, encounterId, row, actionType);
	}

	public String getSystem() {
		return system;
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
	public QueryParams insertQuery(String updateUser) {
		return new QueryParams("insert into emr_review_of_system ( encounter_id, row, system, description, create_t, create_u ) values (?, ?, ?, ?, now(), ?)", 
				new Object[]{getEncounterId(), getRow(), getSystem(), getDescription(), updateUser });
	}

	@Override
	public QueryParams updateQuery(String updateUser) {
		return new QueryParams("update emr_review_of_system set system=?, description=? where encounter_id=? and row=?", 
				new Object[]{getSystem(), getDescription(), getEncounterId(), getRow()});
	}
	
	@Override
	public String getTableName() {
		return "emr_review_of_system";
	}

	@Override
	public QueryParams refreshQuery() {
		return new QueryParams( "select encounter_id, row, system, description from emr_review_of_system where encounter_id=?", 
				new Object[]{getEncounterId()} );
	}

	@Override
	public EMRTableBean getBeanFromResultSet(ResultSet rs) throws SQLException {
		return new EMRReviewOfSystemBean(rs.getInt("encounter_id"), rs.getInt("row"), null, rs.getString("system"), rs.getString("description"));
	}

	@Override
	public void addDataToJson(JSONObject json) throws JSONException {
		json.put("system", getSystem());
		json.put("description", getDescription());
	}
}