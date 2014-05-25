package com.fort.emr;

import com.fort.sec.action.BaseAction;

public class EMRPatSearchResetAction extends BaseAction {

	@Override
	public String doExecute() {
		getSession().remove("emrPatSearchResults");
		getSession().remove("emrPatSearchBean");
		logInfo("cleaned up emrPatSearchResults & emrPatSearchBean");
		return "empty";
	}

	@Override
	protected String getCurrentFunctionName() {
		return "EMR";
	}
}
