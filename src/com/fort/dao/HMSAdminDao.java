package com.fort.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.fort.common.exception.BusinessException;
import com.fort.common.exception.SystemException;
import com.fort.sec.User;

public class HMSAdminDao extends BaseDao {

	private final static HMSAdminDao _inst = new HMSAdminDao();

	private HMSAdminDao() {
	};

	public static HMSAdminDao getDaoInst() {
		return _inst;
	}

	@Override
	public Object toDataObject(ResultSet rs) throws SQLException {
		return new User(rs.getString("user_id"), rs.getString("user_n"),
				rs.getInt("role_id"), rs.getString("email"),
				rs.getString("pwd"), rs.getBoolean("active"),
				rs.getString("update_n"), rs.getTime("update_t"));
	}

	public User findHmsUser(String uid) throws SystemException,
			BusinessException {
		User u = (User) this.getRowByCriteria(
				"select * from auth where user_id=? and active = 1",
				new Object[] { uid });
		return u;
	}

	public User createHmsUser(User u, String createdBy) throws SystemException,
			BusinessException {
		if (findHmsUser(u.getuId()) == null) {
			Object[] o = new Object[] { u.getuId(), u.getPwd(), u.getRoleId(),
					u.getName(), u.getEmail(), createdBy, createdBy };
			this.insertRowByQuery(
					"insert into auth values(?,?,?,?,?,?,now(),?,now(),1)", o);
		} else {
			throw new BusinessException("User with userid [" + u.getuId()
					+ "] already exists");
		}
		return u;
	}

	@SuppressWarnings("unchecked")
	public List<User> getHMSUsersList() throws SystemException {
		return (List<User>) getListByCriteria(
				"select user_id, user_n, role_id, email, update_n, update_t, pwd, active from auth;",
				EMPTY_OBJECT_ARRAY);
	}

	public boolean deactivateUser(String uid) throws SystemException {
		if (uid != null) {
			System.out.println("update the data for uid: " + uid);
			this.insertRowByQuery("update auth set active=0 where user_id='"
					+ uid + "'", EMPTY_OBJECT_ARRAY);
		}
		return true;
	}

	public boolean activateUser(String uid) throws SystemException {
		if (uid != null) {
			System.out.println("update the data for uid: " + uid);
			this.insertRowByQuery("update auth set active=1 where user_id='"
					+ uid + "'", EMPTY_OBJECT_ARRAY);
		}
		return true;
	}

	public boolean changePwd(String uId, String newPwd) throws SystemException {
		return this.insertRowByQuery("update auth set pwd=? where user_id=? and active=1", new Object[]{newPwd, uId} ) == 1;
	}

}
