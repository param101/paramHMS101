package com.fort.emr;

import com.fort.sec.action.BaseAction;

public class EMRPatSearchAction extends BaseAction {

	@Override
	public String doExecute() {
		logInfo("EMRPatSerarchAction: show form");
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "emr-pat-search-page";
	}

	@Override
	protected String getCurrentFunctionName() {
		return "EMR";
	}
}
