package com.fort.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.fort.common.exception.SystemException;

public class CachingDao<K, V> extends BaseMapsDao<K,V> {

	private CachingDao(){};
	private static CachingDao<String,String> _inst = new CachingDao<String,String>();
	public static CachingDao<String,String> getDaoInst(){
		return _inst;
	}
	
	public Map<String,String> getICDCodes() throws SystemException{
		
		return new BaseMapsDao<String, String>().getMapByCriteria("select icd, description from ICD_CODES", new Object[]{}, new MapConstructor<String, String>() {
			@Override
			public String getValue(ResultSet rs) throws SQLException {
				return String.format("%s [%s]", rs.getString("description"), rs.getString("icd"));
			}
			
			@Override
			public String getKey(ResultSet rs) throws SQLException{
				return rs.getString("icd");
			}
		});
	}
}
