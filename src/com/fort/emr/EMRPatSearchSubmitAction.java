package com.fort.emr;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fort.common.exception.SystemException;
import com.fort.dao.PatientDao;
import com.fort.sec.action.BaseAction;
import com.fort.tos.Patient;

public class EMRPatSearchSubmitAction extends BaseAction {

	@Override
	public String doExecute() throws SystemException {
		logInfo("searching patients...");
		logInfo("search criteria : " + getPatientBean());
		
		List<Patient> results;
		if( getPatientBean() == null ) {
			results = new ArrayList<Patient>();
		} else {
			results = PatientDao.getDaoInst().findPatientsByCriteria(getPatientBean());
			logInfo("results from dymydata call : " + results);
		}
		getSession().put("emrPatSearchResults", results);
		getSession().put("emrPatSearchBean", getPatientBean());	
		return "emr-pat-search-results";
	}

	private boolean validateForInputs(){
		if(fName == null && "".equals(fName.trim()) && dob == null && lName == null && "".equals( lName.trim() )&& phone == null && email == null && patId == null )
			return false;
		return true;
	}
	
	public Patient getPatientBean() {
		if (bean != null)
			return bean;
		if (validateForInputs()) {
			synchronized (this) {
				bean = new Patient(null,fName,null,lName,null,null,null,null,null,null, phone, email,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,patId);
			}
		}
		return bean;
	}

	public String toString() {
		return bean.toString();
	}

	private String fName;
	private String lName;
	private Date dob;
	private Integer patId;
	private String phone;
	private String email;
	private Patient bean;

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public Integer getPatId() {
		return patId;
	}

	public void setPatId(Integer patId) {
		this.patId = patId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	protected String getCurrentFunctionName() {
		return "EMR";
	}

}
