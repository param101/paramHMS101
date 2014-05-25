package com.fort.emr;

import com.fort.sec.action.BaseAction;

public class EMRPatNewAction extends BaseAction {

	@Override
	public String doExecute() {
		return "emr-pat-new-page";
	}

	@Override
	protected String getCurrentFunctionName() {
		return "EMR";
	}
}
