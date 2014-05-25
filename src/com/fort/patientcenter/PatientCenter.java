package com.fort.patientcenter;

import com.fort.common.exception.BusinessException;
import com.fort.common.exception.SystemException;
import com.fort.dao.PatientDao;
import com.fort.sec.action.BaseAction;
import com.fort.tos.Patient;
import static com.fort.consts.JSPConstants.NAV_FROM_DASHBOARD;
import static com.fort.consts.JSPConstants.NAV_FROM_EMR_SEARCH;

public class PatientCenter extends BaseAction {

	private Integer patId;
	private Integer nav; //NAV_ constants in JSPConstants.java
	
	@Override
	public String doExecute() throws SystemException {
		logInfo(this);
		if(patId==null || patId == 0){
			return "patientcentermain-page";
		}
		Patient pat;
		try {
			pat = PatientDao.getDaoInst().findPatientByPatId(patId);
		} catch (BusinessException e) {
			addErrToReq("Error occured while retrieving Patient info by PatId: "+ e.getMessage());
			e.printStackTrace();
			return "error";
		}
		getSession().put("patientInfo", pat );

		// this is required to track where the emr encounter request originated from so that the back button will take back to the same page
		if(nav!=null){
			switch(nav){
			case NAV_FROM_DASHBOARD:
			case NAV_FROM_EMR_SEARCH:
				getRequest().put("nav",nav);
				break;
			}
		}
		return "patientcentermain-page";
	}
	
	@Override
	public String toString() {
		return String.format("PatientCenter [patId=%s]", patId);
	}

	@Override
	protected String getCurrentFunctionName() {
		return "EMR";
	}
	
	public Integer getPatId() {
		return patId;
	}

	public void setPatId(Integer patId) {
		this.patId = patId;
	}

	public Integer getNav() {
		return nav;
	}

	public void setNav(Integer nav) {
		this.nav = nav;
	}

}
