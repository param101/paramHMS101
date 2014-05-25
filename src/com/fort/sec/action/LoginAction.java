package com.fort.sec.action;

import com.fort.common.exception.BusinessException;
import com.fort.common.exception.SystemException;
import com.fort.sec.Func;
import com.fort.sec.Role;
import com.fort.sec.User;
import com.fort.sec.admin.SessionTracker;

public class LoginAction extends BaseAction {
	private String uid;
	private String pwd;

	public String doExecute() throws SystemException {
		logInfo("LoginAction: " + this.uid);
		if (uid == null)
			return "login-page";

		User u;
		try {
			u = User.getUser(uid);
			if (u == null) {
				addErrToReq("Invalid userid/password. please try again(userid:firstid, pwd: password)");
				return "login-page";
			}
			;
		} catch (SystemException e) {
			addErrToReq("Error occured while finding user in DB: "
					+ e.getMessage());
			e.printStackTrace();
			return "login-page";
		} catch (BusinessException e) {
			addErrToReq("Error occured while logging in the user: "
					+ e.getMessage());
			e.printStackTrace();
			return "login-page";
		}

		if (u.getPwd() != null && !u.getPwd().equals(pwd)) {
			addErrToReq("Invalid userid / password. please try again with correct userid / password");
			return "login-page";
		}

		logInfo(new StringBuilder("login successfull for user: ").append(uid).toString());
		Role role = Role.getRole(u.getRoleId());
		Func defaultFunc = role.getFunctions().get(0);
		getSession().put("tracker", new SessionTracker(uid));
		getSession().put("user", u);
		getSession().put("role", role);

		getSession().put("currentFunc", defaultFunc.getFuncName());
		addMsgToReq("Welcome user");
		return pageNameFromUrl(defaultFunc.getActionUrl());
	}

	public String getUid() {
		return uid;
	}

	protected Role getRole() {
		return (Role) getSession().get("role");
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	protected String getCurrentFunctionName() {
		if( getRole() != null )
			return getRole().getFunctions().get(0).getFuncName();
		return null;
	}
}
