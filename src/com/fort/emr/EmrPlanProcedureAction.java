package com.fort.emr;

import static com.fort.consts.CommonConstants.ACTION_TYPE_DELETE;
import static com.fort.consts.CommonConstants.ACTION_TYPE_REFRESH;
import static com.fort.consts.CommonConstants.ACTION_TYPE_SAVE;

import java.sql.Date;

import com.fort.common.exception.BusinessException;
import com.fort.common.exception.SystemException;
import com.fort.emr.bean.EmrPlanProcedureBean;
import com.fort.emr.dao.EMRDao;
import com.fort.sec.action.BaseAction;

public class EmrPlanProcedureAction extends BaseAction {

	private Integer encounterId;
	private String nameOfSurgery;
	private String operatingSurgeon;
	private String assistantSurgeon;
	private String anesthesist;
	private String indicationOfSurgery;
	private String description;
	private String actionType;
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
			EmrPlanProcedureBean criteriaBean = getBean();
			try {
				int r = EMRDao.getDaoInst().savePlanProcedureData(criteriaBean, getUser().getuId());
				logFine( "save emr-plan-procedure: "+ this + " (return value = "+r+")" );
				EmrPlanProcedureBean bean = EMRDao.getDaoInst().getPlanProcedureData(encounterId);
				if( bean != null )
					getRequest().put("emrPlanProcedure", bean);
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
		return "emr-plan-procedure";
	}

	private EmrPlanProcedureBean getBean(){
		return new EmrPlanProcedureBean(encounterId, nameOfSurgery, operatingSurgeon, assistantSurgeon, anesthesist, indicationOfSurgery, description, actionType);
	}
	
	@Override
	public String toString() {
		return String.format("EmrPlanProcedureAction [encounterId=%s, nameOfSurgery=%s, operatingSurgeon=%s, assistantSurgeon=%s, anesthesist=%s, indicationOfSurgery=%s, description=%s, actionType=%s, isLocked=%s, user=%s]", encounterId,
				nameOfSurgery, operatingSurgeon, assistantSurgeon, anesthesist, indicationOfSurgery, description, actionType, isLocked, user);
	}

	@Override
	protected String getCurrentFunctionName() {
		return "EMR";
	}

	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}

	public String getNameOfSurgery() {
		return nameOfSurgery;
	}

	public void setNameOfSurgery(String nameOfSurgery) {
		this.nameOfSurgery = nameOfSurgery;
	}

	public String getOperatingSurgeon() {
		return operatingSurgeon;
	}

	public void setOperatingSurgeon(String operatingSurgeon) {
		this.operatingSurgeon = operatingSurgeon;
	}

	public String getAssistantSurgeon() {
		return assistantSurgeon;
	}

	public void setAssistantSurgeon(String assistantSurgeon) {
		this.assistantSurgeon = assistantSurgeon;
	}

	public String getAnesthesist() {
		return anesthesist;
	}

	public void setAnesthesist(String anesthesist) {
		this.anesthesist = anesthesist;
	}

	public String getIndicationOfSurgery() {
		return indicationOfSurgery;
	}

	public void setIndicationOfSurgery(String indicationOfSurgery) {
		this.indicationOfSurgery = indicationOfSurgery;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}
	
}
