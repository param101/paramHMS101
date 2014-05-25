package com.fort.sec.admin;

import com.fort.common.exception.SystemException;
import com.fort.sec.action.BaseAction;


public class HMSAdminAction extends BaseAction {

	@Override
	public String doExecute() throws SystemException {
		return "hmsadmin";
	}
	@Override
	protected String getCurrentFunctionName() {
		return "Admin";
	}
}
