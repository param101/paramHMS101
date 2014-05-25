package com.fort.sec;

import com.fort.common.exception.BusinessException;
import com.fort.common.exception.SystemException;
import com.fort.dao.HMSAdminDao;
import com.fort.sec.action.BaseAction;

public class ChangePwdAction extends BaseAction {

	private String oldPwd;
	private String newPwd;
	private String confirmNewPwd;
	
	@Override
	public String doExecute() throws SystemException {
		logInfo(this);
		if( oldPwd == null && newPwd == null && confirmNewPwd == null )
			return "changepwd"; // user just clicked on the menu item.
		if( ! validate() )
			return "changepwd";
		HMSAdminDao dao = HMSAdminDao.getDaoInst();
		if( dao.changePwd( getUser().getuId(), newPwd ) ){
			addMsgToReq("Your Password change successfully. Please don't forget :) " );
			try {
				User.refreshUser(getUser().getuId());
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		} else {
			addErrToReq("Some error occured in changing your password. Please try checking with your admin" );
		}
		return "changepwd";
	}

	private boolean validate(){
		boolean valid = true;
		if( ! getUser().getPwd().equals(oldPwd) ){
			addErrToReq("Looks like you entered your 'Current Password' incorrectly" );
			valid = false;
		};
		if( newPwd!= null && ! newPwd.equals(confirmNewPwd)){
			addErrToReq("Looks like the 'Choose a new Password' and 'Type new Password Again' do not match. Please try again" );
			valid = false;
		}
		return valid;
	}
	
	@Override
	protected String getCurrentFunctionName() {
		return "MyAccount";
	}

	@Override
	public String toString() {
		return String.format("ChangePwdAction [oldPwd=%s, newPwd=%s, confirmNewPwd=%s]", oldPwd, newPwd, confirmNewPwd);
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getConfirmNewPwd() {
		return confirmNewPwd;
	}

	public void setConfirmNewPwd(String confirmNewPwd) {
		this.confirmNewPwd = confirmNewPwd;
	}
}
