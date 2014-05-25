package com.fort.patientmanagement;

import com.fort.sec.action.BaseAction;

public class AddNewPatientAction extends BaseAction {

	@Override
	public String doExecute() {
		
		return "add-new-patient-page";
	}

	@Override
	protected String getCurrentFunctionName() {
		return "Patient Management";
	}
}
