package com.fort.emr;

import java.sql.Date;
import java.util.List;

import com.fort.common.exception.BusinessException;
import com.fort.common.exception.SystemException;
import com.fort.dao.PatientDao;
import com.fort.emr.bean.EmrPlanProcedureBean;
import com.fort.emr.bean.EmrPlanTreatReferralsBean;
import com.fort.emr.dao.EMRDao;
import com.fort.sec.action.BaseAction;
import com.fort.tos.Patient;
import com.fort.util.DateUtils;

public class EmrAction extends BaseAction {
	private Integer patId;
	private Integer encounterId;
	
	public String doExecute() throws SystemException {
		logInfo(this);
		Encounter currentEncounter;
		if(encounterId != null && encounterId != 0){
			try {
				currentEncounter = EMRDao.getDaoInst().getPatientEncounterByEncounterId(encounterId);
				getSession().put("currentEncounter", currentEncounter);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "error";
			}
		} else {
			if(patId == null || patId == 0 ){
				patId = (Integer) getSession().get("patId-new-encounter");
				getSession().remove("patId-new-encounter");
			}
			List<Encounter> encounterList = EMRDao.getDaoInst().getPatientEncounters(patId); 
			logInfo("encounterList: " + encounterList);
			if(encounterList == null || encounterList.isEmpty()){
				Patient pat;
				try {
					pat = PatientDao.getDaoInst().findPatientByPatId(patId);
				} catch (BusinessException e) {
					addErrToReq( e.getMessage() );
					e.printStackTrace();
					return "error";
				}
				getRequest().put("patName", pat.getFname() + " " + pat.getLname() );
				return "emr-new-encounter";
			} else {
				encounterId=encounterList.get(0).getEncounterId();
			}
			
			getSession().put("encounterDates", encounterList);
			currentEncounter = getEncounterByEncounterDate(encounterId, encounterList);
			getSession().put("currentEncounter", currentEncounter);
		}

		// TODO fix this ugly stuff
		refreshEMRPlanProcedure( currentEncounter.getEncounterId());
		refreshEMRPlanTreatReferrals( currentEncounter.getEncounterId());

		getSession().put("today", encounterDateToString(DateUtils.today()));
		return "emr-page";
	}

	private Encounter getEncounterByEncounterDate( Integer encounterId, List<Encounter> encountersList ){
		for(Encounter e : encountersList){
			if(e.getEncounterId().equals(encounterId)){
				return e;
			}
		}
		return null;
	}

	private String encounterDateToString(Date encounterDate){
		return DateUtils.dateToStringConvert(encounterDate,"MM/dd/yyyy");
	}

	private void refreshEMRPlanProcedure( Integer encounterId ) throws SystemException{
		//TODO fix this
		try {
			EmrPlanProcedureBean bean = EMRDao.getDaoInst().getPlanProcedureData( encounterId );
			if( bean != null )
				getRequest().put("emrPlanProcedure", bean);
		} catch (BusinessException e) {
			addErrToReq( e.getMessage() );
			e.printStackTrace();
			//return "error";
		}
	}
	
	private void refreshEMRPlanTreatReferrals( Integer encounterId ) throws SystemException{
		//TODO fix this
		try {
			EmrPlanTreatReferralsBean bean = EMRDao.getDaoInst().getPlanTreatReferralsData( encounterId );
			if( bean != null )
				getRequest().put("emrPlanTreatReferrals", bean);
		} catch (BusinessException e) {
			addErrToReq( e.getMessage() );
			e.printStackTrace();
			//return "error";
		}
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

	@Override
	public String toString() {
		return String.format("EmrAction [patId=%s, encounterId=%s]", patId, encounterId);
	}

	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}
}
