package com.fort.roommgmt;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.fort.common.exception.BusinessException;
import com.fort.common.exception.SystemException;
import com.fort.dao.PatientDao;
import com.fort.sec.action.BaseAction;
import com.fort.tos.Patient;

public class RoomBookRequestAction extends BaseAction {
	
	private Integer patId;
	private String fname;
	private String lname;
   	
	public Integer getPatId() {
		return patId;
	}



	public void setPatId(Integer patId) {
		this.patId = patId;
	}



	public String getFname() {
		return fname;
	}



	public void setFname(String fname) {
		this.fname = fname;
	}



	public String getLname() {
		return lname;
	}



	public void setLname(String lname) {
		this.lname = lname;
	}


	@Override
	public String doExecute() throws SystemException {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("currFunc",this.getCurrentFunctionName());
		
		
		Patient pat;
		try {
			pat = PatientDao.getDaoInst().findPatientByPatId(patId);
	
		
			System.out.println(pat);
		} catch (BusinessException e) {
			addErrToReq("Error occured while retrieving Patient info by PatId: "+ e.getMessage());
			e.printStackTrace();
			return "error";
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
			return "error";
		}
		super.getSession().put("patientbean", pat );
	
		return "room_booking";
	
	}

	
	
	@Override
	protected String getCurrentFunctionName() {
		return "Room Management";
	}
	
	

}
