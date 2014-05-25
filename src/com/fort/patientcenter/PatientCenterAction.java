package com.fort.patientcenter;

import java.sql.Date;
import java.util.List;

import com.fort.common.exception.BusinessException;
import com.fort.common.exception.SystemException;
import com.fort.dao.EncounterDao;
import com.fort.dao.ImmunizationDao;
import com.fort.dao.MedicationsDao;
import com.fort.dao.PatientDao;
import com.fort.sec.action.BaseAction;
import com.fort.tos.Encounter;
import com.fort.tos.Immunizations;
import com.fort.tos.Medications;
import com.fort.tos.Patient;

public class PatientCenterAction extends BaseAction {
	
	
	
	private  String title;
	private  String fname;
	private  Date dob;
	private  String gender;
	private  String mname;
	private  String lname;
	private  String address;
	private  String city;
	private  String state;
	private  String zip_cd;
	private  String phone;
	private  String email;
	private  Integer patId;
	private  Integer age; //calculated
	// added by rahul for patient center
	private  String ins_carrier;
	private  String ins_groupNumber;
	private  String ins_memberNumber;
	private  String ins_subscriber;
	private  String ins_relationship;
	private  String emrginfo_name;
	private  String emrginfo_relation;
	private  String emrginfo_phone;
	private  String emrginfo_email;
	
	private  String maritalStat;
	private  String religion;
	private  String diet;
	private  String profession;
	private  String income;
	private  Patient bean;

	
	@Override
	public String doExecute()
	{
		logInfo("+++++++++++++++++++++++++++++patient center++++++++++++++++++++++++++++++++++++++++++++++");
		
		 Patient p = new Patient(title, fname, mname, lname, dob, gender, address, city, state, zip_cd, phone, email, age,ins_carrier, ins_groupNumber, ins_memberNumber, ins_subscriber, ins_relationship, emrginfo_name, emrginfo_relation, emrginfo_phone, emrginfo_email, maritalStat, religion, diet, profession, income,patId);
		 System.out.println(p);
			try {
				
				PatientDao.getDaoInst().updatePatientInfo(p,user.getuId());
			
				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+super.getSession().get("patientInfo"));
				
			}
			catch (BusinessException e) {
				addErrToReq("Error occured while retrieving Patient info by PatId: "+ e.getMessage());
				e.printStackTrace();
				return "error";
			}
			catch(Exception e)
			{
				
				e.printStackTrace();
				return "error";
			}
			return "success-page";
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
	public String toString() {
		return String
				.format("PatientCenterAction [title=%s, fname=%s, dob=%s, gender=%s, mname=%s, lname=%s, address=%s, city=%s, state=%s, zip_cd=%s, phone=%s, email=%s, patId=%s, age=%s, ins_carrier=%s, ins_groupNumber=%s, ins_memberNumber=%s, ins_subscriber=%s, ins_relationship=%s, emrginfo_name=%s, emrginfo_relation=%s, emrginfo_phone=%s, emrginfo_email=%s, maritalStat=%s, religion=%s, diet=%s, profession=%s, income=%s, bean=%s]",
						title, fname, dob, gender, mname, lname, address, city,
						state, zip_cd, phone, email, patId, age, ins_carrier,
						ins_groupNumber, ins_memberNumber, ins_subscriber,
						ins_relationship, emrginfo_name, emrginfo_relation,
						emrginfo_phone, emrginfo_email, maritalStat, religion,
						diet, profession, income, bean);
	}


	@Override
	protected String getCurrentFunctionName() {
		return "EMR";
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getFname() {
		return fname;
	}




	public void setFname(String fname) {
		this.fname = fname;
	}




	public Date getDob() {
		return dob;
	}




	public void setDob(Date dob) {
		this.dob = dob;
	}




	public String getGender() {
		return gender;
	}




	public void setGender(String gender) {
		this.gender = gender;
	}




	public String getMname() {
		return mname;
	}




	public void setMname(String mname) {
		this.mname = mname;
	}




	public String getLname() {
		return lname;
	}




	public void setLname(String lname) {
		this.lname = lname;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public String getCity() {
		return city;
	}




	public void setCity(String city) {
		this.city = city;
	}




	public String getState() {
		return state;
	}




	public void setState(String state) {
		this.state = state;
	}




	public String getZip_cd() {
		return zip_cd;
	}




	public void setZip_cd(String zip_cd) {
		this.zip_cd = zip_cd;
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




	public Integer getPatId() {
		return patId;
	}




	public void setPatId(Integer patId) {
		this.patId = patId;
	}




	public Integer getAge() {
		return age;
	}




	public void setAge(Integer age) {
		this.age = age;
	}




	public String getIns_carrier() {
		return ins_carrier;
	}




	public void setIns_carrier(String ins_carrier) {
		this.ins_carrier = ins_carrier;
	}




	public String getIns_groupNumber() {
		return ins_groupNumber;
	}




	public void setIns_groupNumber(String ins_groupNumber) {
		this.ins_groupNumber = ins_groupNumber;
	}




	public String getIns_memberNumber() {
		return ins_memberNumber;
	}




	public void setIns_memberNumber(String ins_memberNumber) {
		this.ins_memberNumber = ins_memberNumber;
	}




	public String getIns_subscriber() {
		return ins_subscriber;
	}




	public void setIns_subscriber(String ins_subscriber) {
		this.ins_subscriber = ins_subscriber;
	}




	public String getIns_relationship() {
		return ins_relationship;
	}




	public void setIns_relationship(String ins_relationship) {
		this.ins_relationship = ins_relationship;
	}




	public String getEmrginfo_name() {
		return emrginfo_name;
	}




	public void setEmrginfo_name(String emrginfo_name) {
		this.emrginfo_name = emrginfo_name;
	}




	public String getEmrginfo_relation() {
		return emrginfo_relation;
	}




	public void setEmrginfo_relation(String emrginfo_relation) {
		this.emrginfo_relation = emrginfo_relation;
	}




	public String getEmrginfo_phone() {
		return emrginfo_phone;
	}




	public void setEmrginfo_phone(String emrginfo_phone) {
		this.emrginfo_phone = emrginfo_phone;
	}




	public String getEmrginfo_email() {
		return emrginfo_email;
	}




	public void setEmrginfo_email(String emrginfo_email) {
		this.emrginfo_email = emrginfo_email;
	}




	public String getMaritalStat() {
		return maritalStat;
	}




	public void setMaritalStat(String maritalStat) {
		this.maritalStat = maritalStat;
	}




	public String getReligion() {
		return religion;
	}




	public void setReligion(String religion) {
		this.religion = religion;
	}




	public String getDiet() {
		return diet;
	}




	public void setDiet(String diet) {
		this.diet = diet;
	}




	public String getProfession() {
		return profession;
	}




	public void setProfession(String profession) {
		this.profession = profession;
	}




	public String getIncome() {
		return income;
	}




	public void setIncome(String income) {
		this.income = income;
	}




	public Patient getBean() {
		return bean;
	}




	public void setBean(Patient bean) {
		this.bean = bean;
	}


	

}
