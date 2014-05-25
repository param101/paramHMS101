package com.fort.emr;

import com.fort.common.exception.BusinessException;
import com.fort.common.exception.SystemException;
import com.fort.dao.PatientDao;
import com.fort.emr.dao.EMRDao;
import com.fort.sec.action.BaseAction;
import com.fort.tos.Patient;

public class EmrNewEncounterAction extends BaseAction {
	private Integer patId;
	
	@Override
	public String doExecute() throws SystemException {
		Patient pat;
		try {
			pat = PatientDao.getDaoInst().findPatientByPatId(patId);
		} catch (BusinessException e) {
			addErrToReq( e.getMessage() );
			e.printStackTrace();
			return "error";
		}
		getRequest().put("patName", pat.getFname() + " " + pat.getLname() );
		getSession().put("patId-new-encounter", patId);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Fix Doc Id below
		int docId = 1;
		try {
			getRequest().put("newEncounter", EMRDao.getDaoInst().createNewEncounterId(new Encounter(0,patId,docId,null,false), super.getUser().getuId()));
		} catch (BusinessException e) {
			addErrToReq(e.getMessage());
			e.printStackTrace();
		}
		return "emr-new-encounter";
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
}
