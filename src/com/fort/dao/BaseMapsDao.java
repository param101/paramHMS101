package com.fort.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.fort.common.exception.SystemException;

public class BaseMapsDao<K,V> extends BaseDao {

	public abstract static class MapConstructor<K,V> {
		private K key;
		private V value;
		
		public abstract K getKey( ResultSet rs) throws SQLException;
		public abstract V getValue(ResultSet rs) throws SQLException;
		
		@Override
		public String toString() {
			return String.format("MapConstructor [key=%s, value=%s]", key, value);
		}
	}
	
	protected Map<K,V> getMapByCriteria( String sql, Object[] prepareParams, MapConstructor<K,V> mapConst ) throws SystemException {
		PreparedStatement lStmt = null;
		ResultSet lRs = null;
		Map<K,V> map = new HashMap<K,V>();
		
		try {
			getConnection();
			if (connection != null) {
				lStmt = connection.prepareStatement(sql);
				doBinds(lStmt, prepareParams);
				System.out.println( "using query: " + sql);
				lRs = lStmt.executeQuery();
				System.out.println("lRs:" + lRs);

				while (lRs.next()) {
						map.put( mapConst.getKey(lRs), mapConst.getValue(lRs));
				}
				System.out.println("size of the map: " + map.size());
			}

		} catch (IOException e) {
			e.printStackTrace();
			// log.fatal("Database error", e);
			throw new SystemException(e.getMessage(), e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException(String.valueOf(e.getErrorCode()), e);
		} finally {
			closeResultSet(lRs);
			closeStatement(lStmt);
			closeConnection();
		}

		return map;
	}
	
	@Override
	public Object toDataObject(ResultSet rs) throws SQLException, SystemException {
		throw new RuntimeException( "should not be used");
	}
	
	public static void main(String[] args) throws SystemException {
		Map<String, String> mm = new BaseMapsDao<String, String>().getMapByCriteria("select icd, description from ICD_CODES", new Object[]{}, new MapConstructor<String, String>() {
			@Override
			public String getValue(ResultSet rs) throws SQLException {
				return rs.getString("description");
			}
			
			@Override
			public String getKey(ResultSet rs) throws SQLException{
				return rs.getString("icd");
			}
		});
		System.out.println("hello");
	}
}
