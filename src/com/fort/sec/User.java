package com.fort.sec;

import static com.fort.consts.CommonConstants.DATA_PREFIX;
import static com.fort.consts.CommonConstants.DATA_SEPERATOR;
import static com.fort.consts.CommonConstants.DATA_SUFFIX;

import java.sql.Time;
import java.util.HashMap;

import com.fort.common.exception.BusinessException;
import com.fort.common.exception.SystemException;
import com.fort.dao.HMSAdminDao;

public class User {
	

	private final String uId;
	private final String name;
	private final int roleId;
	private final String email;
	private final String pwd;
	private final boolean active;
	private String updateId;
	private Time updateTime;
	

	private static final HashMap<String, User> USER_CACHE = new HashMap<String, User>();

	public String getuId() {
		return uId;
	}

	public String getName() {
		return name;
	}

	public int getRoleId() {
		return roleId;
	}
	
	public String getUpdateId() {
		return updateId;
	}

	public Time getUpdateTime() {
		return updateTime;
	}
	
	public User(String uId, String name, int roleId, String email, String pwd, boolean active) {
		super();
		this.uId = uId;
		this.name = name;
		this.roleId = roleId;
		this.email = email;
		this.pwd = pwd;
		this.active = active;
		addUserToCache(this);
	}
	
	public User(String uId, String name, int roleId, String email, String pwd, boolean active, String updateId, Time updateTime) {
		this(uId, name, roleId, email, pwd, active );
		this.updateId = updateId;
		this.updateTime = updateTime;
		
	}

	public static User getUser(String uId) throws SystemException, BusinessException {
		if( isUserInCache(uId)) {
			return getFromCache(uId);
		} else {
			User u = HMSAdminDao.getDaoInst().findHmsUser(uId);
			synchronized (User.class) {
				addUserToCache(u);
			}
			return u;
		}
	}

	public String getEmail() {
		return email;
	}

	public String getPwd() {
		return pwd;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [uId=").append(uId).append(", name=").append(name)
				.append(", roleId=").append(roleId).append(", email=")
				.append(email).append("]");
		return builder.toString();
	}

	public static void main(String[] args) throws SystemException, BusinessException {
		System.out.println(User.getUser("mhs"));
		System.out.println(User.getUser("hms"));
		System.out.println(User.getUser("mhs"));
		System.out.println(User.getUser("hsm"));
	}
	
	public String toDataTableRow(){
		return toString(DATA_PREFIX, DATA_SEPERATOR, DATA_SUFFIX);
	}
	private String toString( final String prefix, final String sep, final String suffix ){
		String roleN;
		try {
			roleN = Role.getRole(roleId).getRoleName();
		} catch (SystemException e) {
			roleN = "Not Able To Find";
		}
		StringBuilder s = new StringBuilder();
		return s.append(prefix).append(uId).append(sep).append(name).append(sep).append(roleN).append(sep).append(email).append(sep).append(updateId).append(sep).append(updateTime).append(sep).append(active?"Deactivate":"Activate").append(suffix).toString();
	}
	public boolean isActive() {
		return active;
	}

	private static synchronized User getFromCache( String u ) {
		return USER_CACHE.get(u.toLowerCase());
	}
	private static synchronized boolean isUserInCache( String u ){
		return USER_CACHE.containsKey(u.toLowerCase());
	}
	private static synchronized void addUserToCache(User o){
		if(o != null && o.isActive())
			USER_CACHE.put(o.getuId().toLowerCase(), o);
	}
	private static synchronized void removeFromCache( String u ){
		USER_CACHE.remove(u.toLowerCase());
	}
	public static boolean deActivateUser(String u) throws SystemException {
		boolean r = HMSAdminDao.getDaoInst().deactivateUser(u);
		if( r && isUserInCache(u)){
			removeFromCache(u);
		}
		return r;
	}
	public static void refreshUser(String u) throws SystemException, BusinessException{
		removeFromCache(u);
		getUser(u);
	}
	public static boolean activateUser(String u) throws SystemException, BusinessException {
		boolean r = HMSAdminDao.getDaoInst().activateUser(u);
		removeFromCache(u);
		getUser(u);
		return false;
	}
}