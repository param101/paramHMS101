package com.fort.emr;

import static com.fort.consts.CommonConstants.ACTION_TYPE_DELETE;
import static com.fort.consts.CommonConstants.ACTION_TYPE_REFRESH;
import static com.fort.consts.CommonConstants.ACTION_TYPE_SAVE;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fort.common.exception.SystemException;
import com.fort.emr.bean.EMRTableBean;
import com.fort.emr.dao.EMRDao;
import com.fort.sec.action.BaseAction;
import com.fort.util.JSONObjectFort;

public abstract class EMRBaseAction extends BaseAction {

	protected Integer row;
	protected Integer encounterId;
	protected String actionType; //save, delete, update, refresh
	protected Boolean isLocked; //true, false
	
	protected abstract boolean validateBean();
	
	@Override
	public final String doExecute() throws SystemException {
		logInfo(this);
		encounterId=encounterId==null?((Encounter)getSession().get("currentEncounter")).getEncounterId():encounterId;
		isLocked=isLocked==null?((Encounter)getSession().get("currentEncounter")).isLocked():isLocked;
		
		if(actionType.equalsIgnoreCase(ACTION_TYPE_REFRESH)){
			EMRTableBean criteriaBean = getBean();
			List<EMRTableBean> data = EMRDao.getDaoInst().refreshData( criteriaBean );
			logInfo("data from table: " + data );
			JSONObject jsonObj = new JSONObjectFort();
			JSONArray jsonArray = new JSONArray();
			try {
				if(data != null) {
					for(EMRTableBean bean : data){
						JSONObject json = new JSONObjectFort();
						json.put("row",bean.getRow());
						bean.addDataToJson(json);
						if(!isLocked)
							json.put("edit", "");//keep this guy empty. required in jsp
						jsonArray.put(json);
					}
				}
				jsonObj.put("aaData", jsonArray);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new SystemException(e.getMessage());
			}
			getRequest().put("jsonData", jsonObj);
			logInfo("jsonArray: "+jsonObj.toString());
			return "json-data";
		} else if(actionType.equalsIgnoreCase(ACTION_TYPE_SAVE)){
			if(isLocked){
				addErrToReq("trying to modify a locked encounter" );
				return null;
			}
			if( ! validateBeanBasic() )
				return null;
			if( ! validateBean() )
				return null;
			if( EMRDao.getDaoInst().saveRecord(getBean(), user.getuId())){
				logInfo("successfully saved the chiefCompliantRecord" );
				//TODO: capture the message to send to user;
				return null;
			} else {
				logInfo("failed to save chiefComplaintRecord" );
				//TODO: capture the message to send to user;
				return null;
			}
		} else if(actionType.equalsIgnoreCase(ACTION_TYPE_DELETE)){
			if(isLocked){
				addErrToReq("trying to modify a locked encounter" );
				return null;
			}
			if( ! validateBeanBasic() )
				return null;
			if( ! validateBean() )
				return null;
			if( EMRDao.getDaoInst().deleteRecord(getBean(), user.getuId())){
				logInfo("successfully saved the chiefCompliantRecord" );
				//TODO: capture the message to send to user;
				return null;
			} else {
				logInfo("failed to save chiefComplaintRecord" );
				//TODO: capture the message to send to user;
				return null;
			}
		} else {
			logInfo("unknown actionType: " + actionType );
		}
		return null;
	}

	private boolean validateBeanBasic() {
		if(row==null||encounterId==null) {
			addErrToReq("invalid row / Patient Id / encounter date - please check" );
			logInfo("validateBeanBasic failed..");
			return false;
		}
		return true;
	}

	protected abstract EMRTableBean getBean();

	@Override
	protected final String getCurrentFunctionName() {
		return "EMR";
	}

	@Override
	public String toString() {
		return String.format("EMRBaseAction [row=%s, encounterId=%s, actionType=%s, isLocked=%s]", row, encounterId, actionType, isLocked);
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
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

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}
}
