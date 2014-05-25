package com.fort.emr.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;

import com.fort.sec.bean.BaseBean;

public abstract class EMRTableBean extends BaseBean implements IRowAware {
	protected Integer encounterId;
	protected Integer row;
	protected String actionType; //save, delete, update, refresh
	
	public EMRTableBean( Integer encounterId, Integer row, String actionType ){
		this.encounterId = encounterId;
		this.row=row;
		this.actionType = actionType;
	}
	
	public abstract boolean isInsertSupported();
	public abstract boolean isDeleteSupported();
	public abstract boolean isUpdateSupported();
	
	public abstract QueryParams insertQuery( String updateUser );
	public abstract QueryParams updateQuery( String updateUser );
	public abstract QueryParams refreshQuery();
	public abstract String getTableName();
	public abstract EMRTableBean getBeanFromResultSet(ResultSet rs) throws SQLException ;
	public abstract void addDataToJson(JSONObject json) throws JSONException;
	
	public static class QueryParams{
		private String query;
		private Object[] params;
		public QueryParams(String query, Object[] params) {
			this.query = query;
			this.params = params;
		}
		public String getQuery() {
			return query;
		}
		public Object[] getParams() {
			return params;
		}
		@Override
		public String toString() {
			final int maxLen = 10;
			return String.format( "QueryParams [query=%s, params=%s]", query,	params != null ? Arrays.asList(params).subList(0, Math.min(params.length, maxLen)) : null);
		}
	}
	
	public Integer getEncounterId() {
		return encounterId;
	}

	public final Integer getRow() {
		return row;
	}

	public final String getActionType()  {
		return actionType;
	}
	
	public final QueryParams deleteQuery( String updateUser ) {
		return new QueryParams("delete from "+getTableName()+" where encounter_id=? and row=?", new Object[]{getEncounterId(), getRow()});
	}
	
	public final QueryParams shiftRowUp(Integer oldRow, Integer newRow ) {
		if(oldRow<= newRow)
			return null;
		return new QueryParams("update "+getTableName()+" set row=? where encounter_id=? and row=?", 
				new Object[]{ newRow, getEncounterId(), oldRow });
	}
}
