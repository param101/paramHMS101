package com.fort.patientmanagement;

import com.fort.sec.action.BaseAction;

public class AddAppMainAction extends BaseAction {

	@Override
	public String doExecute() {
		
		getSession().put("currentFunc", "Patient Management");
		return "addapointmentmain-page";
	}

	@Override
	protected String getCurrentFunctionName() {
		return "Patient Management";
	}
}
