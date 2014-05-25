package com.fort.patientcenter;

import java.util.List;

import com.fort.common.exception.BusinessException;
import com.fort.common.exception.SystemException;
import com.fort.dao.MedicationsDao;
import com.fort.dao.PatientDao;
import com.fort.sec.action.BaseAction;
import com.fort.tos.Medications;
import com.fort.tos.Patient;

public class MedicationsAction extends BaseAction {

	
	@Override
	public String doExecute() throws SystemException {
		logInfo("searching patients...");
		
		
		List<Medications> results;
		
			//results =  PatientCenterDao.getDaoInst().findMedicationsList(((Patient)getSession().get("patientInfo")).getPatId());
			try {
				results = MedicationsDao.getDaoInst().getMedicationsList(((Patient)super.getSession().get("patientInfo")).getPatId());
				getSession().put("medication_list", results);
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "error";
			}
	
		
		
		return "medications_search";
	}

	
	
	@Override
	protected String getCurrentFunctionName() {
		return "EMR";
	}
	
	

}
