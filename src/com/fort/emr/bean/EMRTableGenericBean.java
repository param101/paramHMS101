package com.fort.emr.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class EMRTableGenericBean extends EMRTableBean {

	public EMRTableGenericBean( Integer encounterId, Integer row, String actionType ){
		super(encounterId, row, actionType);
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
		return null;
	}

	@Override
	public QueryParams updateQuery(String updateUser) {
		return null;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public QueryParams refreshQuery() {
		return null;
	}
	@Override
	public EMRTableBean getBeanFromResultSet(ResultSet rs) throws SQLException {
		return null;
	}
	@Override
	public void addDataToJson(JSONObject json) throws JSONException {
	}
}