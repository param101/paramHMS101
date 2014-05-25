package com.fort.emr;

import com.fort.sec.action.BaseAction;

public class EMRSearchAction extends BaseAction {
	public String doExecute() {
		System.out.println("emraction please...");
		getSession().put("currentFunc", "EMR");
		return "emrmainpage-page";
	}

	@Override
	protected String getCurrentFunctionName() {
		return "EMR";
	}
}
