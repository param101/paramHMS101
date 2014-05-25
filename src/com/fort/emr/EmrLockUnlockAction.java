package com.fort.emr;

import com.fort.common.exception.BusinessException;
import com.fort.common.exception.SystemException;
import com.fort.emr.bean.EMRTableBean;
import com.fort.emr.dao.EMRDao;
import com.fort.sec.action.BaseAction;

public class EmrLockUnlockAction extends BaseAction {
	private Boolean lock;
	private Integer encounterId;

	@Override
	public String doExecute() throws SystemException {
		try {
			Encounter enc = EMRDao.getDaoInst().getPatientEncounterByEncounterId(encounterId);
			if( lock.equals(enc.isLocked() ) ) {
				if(lock)
					addErrToReq("already locked" );
				else
					addErrToReq("already unlocked");
				return "error";
			}
			int ret = EMRDao.getDaoInst().updateLockUnlockEncounter(encounterId, lock, enc.getEncounterTime(), user.getuId());
			if( ret != 0 ){
				return "patient-center-encounter";
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			addErrToReq(e.getMessage());
			return "error";
		}
		return null;
	}

	@Override
	protected String getCurrentFunctionName() {
		return "EMR";
	}

	public Boolean getLock() {
		return lock;
	}

	public void setLock(Boolean lock) {
		this.lock = lock;
	}

	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}
	
}
