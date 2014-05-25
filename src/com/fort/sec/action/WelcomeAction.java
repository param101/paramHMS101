package com.fort.sec.action;

import com.fort.common.exception.SystemException;

public class WelcomeAction extends BaseAction {

	@Override
	public String doExecute() throws SystemException {
		logInfo("welcome user: "+getUser());
		return "welcome";
	}

	@Override
	protected String getCurrentFunctionName() {
		return null;
	}

}
