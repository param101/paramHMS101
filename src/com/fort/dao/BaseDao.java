package com.fort.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Logger;

import com.fort.common.exception.BusinessException;
import com.fort.common.exception.SystemException;

public abstract class BaseDao {
	protected final static Logger log = Logger.getLogger(BaseDao.class.getName());
	protected Connection connection = null;
	protected final int ERROR = -1;
	protected final static Object[] EMPTY_OBJECT_ARRAY = new Object[] {};
	private final static String connectionUrl;
	static {
		String mode = System.getenv("HMS_MODE");
		if (mode != null && mode.length() > 0)
			connectionUrl = "jdbc:mysql://localhost:3306/HMS";
		else {
			//connectionUrl = "jdbc:mysql://aavscivlx8mlma.c3ize5mkvvb8.us-west-2.rds.amazonaws.com:3306/HMS";
			String host = System.getenv("RDS_HOSTNAME");
			String port = System.getenv("RDS_PORT");
			//connectionUrl = "jdbc:mysql://"+host+":"+port+"/HMS";
			connectionUrl = "jdbc:mysql://aax6wary452nx3.c3ize5mkvvb8.us-west-2.rds.amazonaws.com:3306/HMS";
		}
		
		System.out.println( "Using Connection to : " + connectionUrl );
	}
	
	private String getUrl() {
		return connectionUrl;
	}

	protected void getConnection() throws SQLException, SystemException,
			IOException {
		if (connection != null) {
			return;
		}
		if (connection == null) {
			try {
				String userid = "hmsuser";
				String password = "hmspass";
				String url = getUrl();
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(url, userid, password);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}

	protected static ResultSet preparedStatementQuery(String q, Object[] b,	Connection connection) throws SystemException {
		PreparedStatement pstmt;

		try {
			pstmt = connection.prepareStatement(q);
		} catch (SQLException e) {
			throw new SystemException(e.getMessage(), e);
		}

		return preparedStatementQuery(pstmt, b);
	}

	public static ResultSet preparedStatementQuery(PreparedStatement pstmt,
			Object[] bindVars) throws SystemException {
		try {
			doBinds(pstmt, bindVars);
		} catch (SQLException e) {
			throw new SystemException(e.getMessage(), e);
		}

		try {
			return pstmt.executeQuery();
		} catch (SQLException e1) {
			throw new SystemException(e1.getMessage(), e1);
		}
	}

	// This function can be used for Insert,Update and Delete operations.
	public int preparedStatementUpdate(String query, Object[] bindVars,
			boolean autoCommit) throws SystemException {
		PreparedStatement pstmt = null;

		try {
			if (connection == null) {
				getConnection();
			}

			connection.setAutoCommit(autoCommit);
			pstmt = connection.prepareStatement(query);
			doBinds(pstmt, bindVars);

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new SystemException(e.getMessage(), e);

			// connection.rollback();
			// return this.ERROR;
		} catch (IOException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeStatement(pstmt);
			log.fine("In close");

			if (autoCommit == true) {
				closeConnection();
			}
		}
	}

	public void commitToDatabase(boolean commit) throws SystemException {
		if (commit = true) {
			try {
				connection.commit();
			} catch (SQLException e) {
				throw new SystemException(e.getMessage(), e);
			}

			closeConnection();
		} else {
			try {
				connection.rollback();
			} catch (SQLException e) {
				throw new SystemException(e.getMessage(), e);
			}

			closeConnection();
		}
	}

	public int preparedStatementUpdate(String query, Object[] bindVars)
			throws SystemException {
		PreparedStatement pstmt = null;

		try {
			if (connection == null) {
				getConnection();
			}

			pstmt = connection.prepareStatement(query);
			doBinds(pstmt, bindVars);

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new SystemException(e.getMessage(), e);
		} catch (IOException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeStatement(pstmt);
			log.fine("In close");
			closeConnection();
		}
	}

	public static void doBinds(PreparedStatement pstmt, Object[] bindVars)
			throws SQLException {
		if (bindVars != null) {
			for (int i = 0; i < bindVars.length; i++) {
				Object o = bindVars[i];

				if (o == null) {
					pstmt.setNull(i + 1, java.sql.Types.VARCHAR);
				} else if (o instanceof java.lang.String) {
					pstmt.setString(i + 1, (String) o);
				} else if (o instanceof java.lang.Integer) {
					pstmt.setInt(i + 1, ((Integer) o).intValue());
				} else if (o instanceof java.lang.Float) {
					pstmt.setFloat(i + 1, ((Float) o).floatValue());
				} else if (o instanceof java.lang.Boolean) {
					pstmt.setBoolean(i + 1, ((Boolean) o).booleanValue());
				} else if (o instanceof java.lang.Long) {
					pstmt.setLong(i + 1, ((Long) o).longValue());
				} else if (o instanceof java.sql.Date) {
					pstmt.setDate(i + 1, ((java.sql.Date) o));
					System.out.println("******************"+pstmt.toString());
				} else if (o instanceof java.sql.Time ) {
					pstmt.setTimestamp(i+1, new java.sql.Timestamp(((java.sql.Time)o).getTime()));
				} else if( o instanceof Timestamp ){
					pstmt.setTimestamp(i+1, ((Timestamp)o));
				}
			}
		}
	}

	public static void doBinds(CallableStatement pstmt, Object[] bindVars)
			throws SQLException {
		if (bindVars != null) {
			int bindVarsLen = bindVars.length;

			for (int i = 0; i < bindVarsLen; i++) {
				Object o = bindVars[i];

				if (o == null) {
					pstmt.setNull(i + 1, java.sql.Types.VARCHAR);
				} else if (o instanceof java.lang.String) {
					pstmt.setString(i + 1, (String) o);
				}
				/*
				 * else if (o instanceof ClobWrapper) { if
				 * (((ClobWrapper)o).fXml == null){ pstmt.setNull(i + 1,
				 * java.sql.Types.VARCHAR); }else{ oracle.sql.CLOB lClob =
				 * toCLOB( ((ClobWrapper)o).fXml, pstmt.getConnection());
				 * pstmt.setClob(i + 1, lClob); } }
				 */
				else if (o instanceof java.lang.Integer) {
					pstmt.setInt(i + 1, ((Integer) o).intValue());
				} else if (o instanceof java.lang.Float) {
					pstmt.setFloat(i + 1, ((Float) o).floatValue());
				} else if (o instanceof java.lang.Boolean) {
					pstmt.setBoolean(i + 1, ((Boolean) o).booleanValue());
				} else if (o instanceof java.lang.Long) {
					pstmt.setLong(i + 1, ((Long) o).longValue());
				} else if (o instanceof java.util.Date) {
					pstmt.setTimestamp(i + 1, new java.sql.Timestamp(
							((java.util.Date) o).getTime()));
				}
			}

			/*
			 * Object o = bindVars[bindVarsLen];
			 * pstmt.registerOutParameter(bindVarsLen,Types.INTEGER);
			 */
		}
	}

	protected void closeConnection() {
		try {
			// log.debug("Closing Connection");
			if (connection != null) {
				connection.close();

				connection = null;
			}

			// log.debug("Closed Connection");
		} catch (SQLException de) {
			de.printStackTrace();
		}
	}

	protected static void closeStatement(Statement pStmt) {
		try {
			if (pStmt != null) {
				pStmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// The below code will convert a resultset into an ArrayList
	// We are doing this so that we can return the arraylist and close the
	// connection.
	// If we close the conection, the resultset becomes null.
	protected Object getListByCriteria(String sql, Object[] prepareParams) throws SystemException {
		PreparedStatement lStmt = null;
		ResultSet lRs = null;
		ArrayList aList = null;

		try {
			getConnection();
			lStmt = connection.prepareStatement(sql);
			doBinds(lStmt, prepareParams);
			lRs = lStmt.executeQuery();

			if (lRs != null) {
				aList = new ArrayList();

				while (lRs.next()) {
					aList.add(this.toDataObject(lRs));
				}

				if (aList.isEmpty() == true) {
					aList = null;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException(e.getMessage(), e);
			
		} catch (IOException e) {
			throw new SystemException(e.getMessage(), e);

			// e.printStackTrace();
		} finally {
			closeResultSet(lRs);
			closeStatement(lStmt);
			closeConnection();
		}

		return aList;
	}

	protected int insertRowByQuery(String sql, Object[] prepareParams)
			throws SystemException {
		try {
			if (connection == null)
				getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			try {
				if( prepareParams.length > 0 )
					doBinds(pstmt, prepareParams);
			} catch (SQLException e) {
				throw new SystemException(e.getMessage(), e);
			}
			try {
				return pstmt.executeUpdate();
			} catch (SQLException e1) {
				throw new SystemException(e1.getMessage(), e1);
			}
		} catch (IOException e) {
			throw new SystemException(e.getMessage(), e);
		} catch (SQLException e) {
			throw new SystemException(String.valueOf(e.getErrorCode()), e);
		} finally {
			closeConnection();
		}

	}

	protected Object getRowByCriteria(String sql, Object[] prepareParams) throws SystemException, BusinessException {
		PreparedStatement lStmt = null;
		ResultSet lRs = null;
		Object obj = null;

		try {
			getConnection();

			int i = 0;

			if (connection != null) {
				lStmt = connection.prepareStatement(sql);
				doBinds(lStmt, prepareParams);
				System.out.println( "using query: " + sql);
				lRs = lStmt.executeQuery();
				System.out.println("lRs:" + lRs);

				while (lRs.next()) {
					i = i + 1;

					if (i == 1) {
						obj = this.toDataObject(lRs);
						System.out.println("obj:" + obj);
					}
				}
			}

			if (i > 1) {
				log.finer(i + " Unexpected rows from database");
				throw new BusinessException(" Unexpected rows from database");
			}

			/*
			 * if(lRs.next()){ log.debug(lRs.getRow());}
			 */
			/*
			 * if (lRs.wasNull()) { lRs.next(); obj = this.toDataObject(lRs);
			 * int i = 0; while (lRs.next()) { i = i + 1; } if (i != 0) {
			 * log.warn(i + " Unexpected rows from database"); } }
			 */
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

		return obj;
	}

	protected static void closeResultSet(ResultSet pRs) {
		try {
			if (pRs != null) {
				pRs.close();
				pRs = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected String encloseInPercentage(String input ){
		return new StringBuilder("%").append(input).append("%").toString();
	}

	protected Timestamp getCurrentTime() {
		return new Timestamp(Calendar.getInstance().getTime().getTime());
	}

	public abstract Object toDataObject(ResultSet rs) throws SQLException, SystemException;
	
}
