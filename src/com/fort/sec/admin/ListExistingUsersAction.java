package com.fort.sec.admin;

import java.util.List;

import com.fort.common.exception.SystemException;
import com.fort.dao.HMSAdminDao;
import com.fort.sec.User;
import com.fort.sec.action.BaseAction;

public class ListExistingUsersAction extends BaseAction {

	@Override
	public String doExecute() throws SystemException {
		List<User> users = HMSAdminDao.getDaoInst().getHMSUsersList();
		super.getRequest().put("hmsusers", users);
		return "hms-list-users";
	}

	@Override
	protected String getCurrentFunctionName() {
		return "Admin";
	}

}
