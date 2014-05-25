package com.fort.patientcenter;

import java.util.List;

import com.fort.common.exception.BusinessException;
import com.fort.common.exception.SystemException;
import com.fort.dao.ImmunizationDao;
import com.fort.dao.PatientDao;
import com.fort.sec.action.BaseAction;
import com.fort.tos.Immunizations;
import com.fort.tos.Patient;

public class ImmunizationsAction extends BaseAction {

	@Override
	public String doExecute() throws SystemException {
		logInfo("searching patients...");

		List<Immunizations> results;
		try {
			results = ImmunizationDao.getDaoInst().getImmunizationList(
					((Patient) super.getSession().get("patientInfo"))
							.getPatId());

			getSession().put("immunizations_list", results);
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}

		return "immunization_search";
	}

	@Override
	protected String getCurrentFunctionName() {
		return "EMR";
	}

}
