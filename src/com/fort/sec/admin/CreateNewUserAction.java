package com.fort.sec.admin;

import com.fort.common.Validations;
import com.fort.common.exception.BusinessException;
import com.fort.common.exception.SystemException;
import com.fort.consts.CommonConstants;
import com.fort.dao.HMSAdminDao;
import com.fort.sec.User;
import com.fort.sec.action.BaseAction;

public class CreateNewUserAction extends BaseAction {
	private String userid;
	private String name;
	private String email;
	private String pwd;
	private String cpwd;
	private String role;
	
	@Override
	public String doExecute() {
		logInfo(this);
		if( !validate() ) {
			return "hms-admin";
		}
		User u = new User(userid, name, Integer.parseInt(role),email, pwd, true );
		try {
			u = HMSAdminDao.getDaoInst().createHmsUser(u, user.getuId());
		} catch (SystemException e) {
			e.printStackTrace();
			addErrToReq("Error creating user: " + e.getMessage());
			return "hms-admin";
		} catch (BusinessException e) {
			e.printStackTrace();
			addErrToReq("Error creating user: " + e.getMessage());
			return "hms-admin";
		}
		addMsgToReq( "UserCreated: " + u.toString());
		return "hms-admin";
	}

	private boolean validate() {
		boolean e = true;
		if( ! Validations.validateUserId(userid)){
			addErrToReq("User ID is invalid");
			e = false;
		}
		if( !Validations.validatePwd(pwd) || !Validations.validatePwd(cpwd)){
			addErrToReq("Password is not per out policy (minlength:"+CommonConstants.PWD_LEN_MIN+", maxlength:"+CommonConstants.PWD_LEN_MAX+")");
			if(!pwd.equals(cpwd)){
				addErrToReq("password & confirm password don't match");
			}
			e = false;
		}
		if( !Validations.validateEmail(email)){
			addErrToReq("Email ID is not per out policy (minlength:"+CommonConstants.EMAIL_LEN_MIN+", maxlength:"+CommonConstants.EMAIL_LEN_MAX+")");
			e = false;
		}
		try{
			int r = Integer.parseInt(role);
			if( !Validations.validateRole(r)){
				addErrToReq("Role ID is not valid. Please try again. If problem persists, please contact your admin" );
				e = false;
			}
		} catch( NumberFormatException n){
			addErrToReq("Perhaps you didn't select a role for the new user. Please try again. If problem persists, please contact your admin" );
			e = false;
		}	
		return e;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setCpwd(String cpwd) {
		this.cpwd = cpwd;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CreateNewUserAction [userid=").append(userid)
				.append(", name=").append(name).append(", email=")
				.append(email).append(", pwd=").append(pwd).append(", cpwd=")
				.append(cpwd).append(", role=").append(role).append("]");
		return builder.toString();
	}

	@Override
	protected String getCurrentFunctionName() {
		return "Admin";
	}
}
