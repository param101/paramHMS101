package com.fort.emr;

import static com.fort.consts.CommonConstants.ACTION_TYPE_DELETE;
import static com.fort.consts.CommonConstants.ACTION_TYPE_REFRESH;
import static com.fort.consts.CommonConstants.ACTION_TYPE_SAVE;

import com.fort.common.exception.BusinessException;
import com.fort.common.exception.SystemException;
import com.fort.emr.bean.EmrPlanProcedureBean;
import com.fort.emr.bean.EmrPlanTreatReferralsBean;
import com.fort.emr.dao.EMRDao;
import com.fort.sec.action.BaseAction;

public class EmrPlanTreatReferralsAction extends BaseAction {

	private Integer encounterId;
	private String actionType;
	private String speciality;
	private String consultant;
	private String sendVia;
	private String reasonForReferral;
	private String internalComments;
	private String letter;
	private Boolean isLocked;
	
	@Override
	public String doExecute() throws SystemException {
		logInfo(this);
		encounterId=encounterId==null?((Encounter)getSession().get("currentEncounter")).getEncounterId():encounterId;
		isLocked=isLocked==null?((Encounter)getSession().get("currentEncounter")).isLocked():isLocked;

		if(actionType == null || actionType.equalsIgnoreCase(ACTION_TYPE_REFRESH)){
			logInfo(this + "refresh..");
			
			try {
				EmrPlanProcedureBean bean = EMRDao.getDaoInst().getPlanProcedureData(encounterId);
				if( bean != null )
					getRequest().put("emrPlanProcedure", bean);
			} catch (BusinessException e) {
				addErrToReq( e.getMessage() );
				e.printStackTrace();
				return "error";
			}
		} else if(actionType.equalsIgnoreCase(ACTION_TYPE_SAVE)){
			logInfo(this + "save..");
			EmrPlanTreatReferralsBean criteriaBean = getBean();
			try {
				int r = EMRDao.getDaoInst().savePlanTreatReferralsData(criteriaBean, getUser().getuId());
				logFine( "save emr-plan-procedure: "+ this + " (return value = "+r+")" );
				EmrPlanTreatReferralsBean bean = EMRDao.getDaoInst().getPlanTreatReferralsData(encounterId);
				if( bean != null )
					getRequest().put("emrPlanTreatReferrals", bean);
			} catch (BusinessException e) {
				addErrToReq( e.getMessage() );
				e.printStackTrace();
				return "error";
			}
		} else if(actionType.equalsIgnoreCase(ACTION_TYPE_DELETE)){
			logInfo(this + "delete..");
			addErrToReq("not implemented yet");
			return "error";
		} else {
			logInfo("unknown actionType: " + actionType );
		}
		return "emr-plan-treat-referrals";
	}

	@Override
	protected String getCurrentFunctionName() {
		return "EMR";
	}

	private EmrPlanTreatReferralsBean getBean(){
		return new EmrPlanTreatReferralsBean(encounterId, actionType, speciality, consultant, sendVia, reasonForReferral, internalComments, letter);
	}

	@Override
	public String toString() {
		return String.format("EmrPlanTreatReferralsAction [encounterId=%s, actionType=%s, speciality=%s, consultant=%s, sendVia=%s, reasonForReferral=%s, internalComments=%s, letter=%s, isLocked=%s, user=%s]", encounterId, actionType, speciality,
				consultant, sendVia, reasonForReferral, internalComments, letter, isLocked, user);
	}

	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getConsultant() {
		return consultant;
	}

	public void setConsultant(String consultant) {
		this.consultant = consultant;
	}

	public String getSendVia() {
		return sendVia;
	}

	public void setSendVia(String sendVia) {
		this.sendVia = sendVia;
	}

	public String getReasonForReferral() {
		return reasonForReferral;
	}

	public void setReasonForReferral(String reasonForReferral) {
		this.reasonForReferral = reasonForReferral;
	}

	public String getInternalComments() {
		return internalComments;
	}

	public void setInternalComments(String internalComments) {
		this.internalComments = internalComments;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}
}