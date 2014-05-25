package com.fort.patientcenter;

import java.util.List;

import com.fort.common.exception.BusinessException;
import com.fort.common.exception.SystemException;
import com.fort.dao.EncounterDao;
import com.fort.dao.PatientDao;
import com.fort.sec.action.BaseAction;
import com.fort.tos.Encounter;
import com.fort.tos.Patient;

public class EncounterAction extends BaseAction {

	
	@Override
	public String doExecute() throws SystemException {
		logInfo("searching patients...");
		System.out.println("*******************************************Encounter*********************************"+((Patient)super.getSession().get("patientInfo")).getPatId());
		
		
		List<Encounter> results;
		
			//results = AppointmentDao.getDaoInst().findEncounterList(((Patient)getSession().get("patientInfo")).getPatId());
			try {
				results = EncounterDao.getDaoInst().findEncounterList(((Patient)super.getSession().get("patientInfo")).getPatId());
			    getSession().put("encounter_list", results);
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "error";
			}
	
		
		
		return "encounter_search";
	}

	
	
	@Override
	protected String getCurrentFunctionName() {
		return "EMR";
	}
	
	
}
