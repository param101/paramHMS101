package com.fort.patientmanagement;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fort.bean.PatientBean;
import com.fort.common.exception.SystemException;
import com.fort.dao.PatientDao;
import com.fort.sec.action.BaseAction;
import com.fort.tos.Patient;

public class AddAppPatSearchSubmitAction extends BaseAction {
	
	
	private String fname;

	private String lname;
	private Date dob;
	private String phone;
	private String email;
	private Patient bean;
	private Integer patId;
	
	
	
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
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

	public Patient getBean() {
		return bean;
	}

	public void setBean(Patient bean) {
		this.bean = bean;
	}

	public Integer getPatId() {
		return patId;
	}

	public void setPatId(Integer patId) {
		this.patId = patId;
	}





	@Override
	public String doExecute() {
		logInfo("searching patients...");
		logInfo("search criteria : " + getPatientBean());
		
		List<Patient> results;
		if( getPatientBean() == null ) {
			results = new ArrayList<Patient>();
			
		} else {
			try {
				logInfo("^^^^^^^^^^^^^^^"+bean);
				results = PatientDao.getDaoInst().findPatientsByCriteria(getPatientBean());
				logInfo("results from dymydata call : " + results);
				
				getSession().put("addAppPatSearchResults", results);
				getSession().put("addAppPatSearchBean", getPatientBean());	
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
		return "app-pat-search-results";
	}

	private boolean validateForInputs(){
		if(fname == null && dob == null && lname == null && phone == null && email == null && patId == null )
			return false;
		return true;
	}
	public Patient getPatientBean() {
		if (bean != null)
			return bean;
		if (validateForInputs()) {
			synchronized (this) {
				 bean = new Patient(null,fname,null,lname,null,null,null,null,null,null, phone, email,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,patId);
			}
		}
		return bean;
	}


	

	@Override
	protected String getCurrentFunctionName() {
		return "EMR";
	}

}
