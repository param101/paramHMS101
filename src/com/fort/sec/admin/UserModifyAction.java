package com.fort.sec.admin;

import com.fort.common.exception.BusinessException;
import com.fort.common.exception.SystemException;
import com.fort.sec.User;
import com.fort.sec.action.BaseAction;

public class UserModifyAction extends BaseAction {

	private String uid;
	private int action;

	@Override
	public String doExecute() throws SystemException {
		logInfo("uid: "+uid + " action: "+ action );
		if (uid.equalsIgnoreCase(this.getUser().getuId())){
			addMsgToReq("You just tried to de-activate yourself?");
			return "hms-list-users";
		}
		
		
		
		if(action==0)
			User.deActivateUser(uid);
		else if(action==1)
			try {
				User.activateUser(uid);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else 
			throw new SystemException("invalid action recieved from the client");
		
		return "hms-list-users";
	}

	@Override
	protected String getCurrentFunctionName() {
		return "Admin";
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

}
