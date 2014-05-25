package com.fort.patientmanagement;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.fort.sec.action.BaseAction;

public class PatManagementAction extends BaseAction {

	@Override
	public String doExecute() {
	
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("currFunc",this.getCurrentFunctionName());
		
	
		return "pat_search";
	}

	@Override
	protected String getCurrentFunctionName() {
		return "Patient Management";
	}
}
