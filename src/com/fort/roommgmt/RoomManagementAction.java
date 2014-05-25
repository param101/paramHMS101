package com.fort.roommgmt;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.fort.common.exception.SystemException;
import com.fort.sec.action.BaseAction;

public class RoomManagementAction extends BaseAction {

	
	@Override
	public String doExecute() throws SystemException {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("currFunc",this.getCurrentFunctionName());
		
	
		return "pat_search";
	
	}

	
	
	@Override
	protected String getCurrentFunctionName() {
		return "Room Management";
	}
	
	

}
