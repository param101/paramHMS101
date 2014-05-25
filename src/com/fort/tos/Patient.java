package com.fort.tos;

import static com.fort.consts.CommonConstants.DATA_PREFIX;
import static com.fort.consts.CommonConstants.DATA_SEPERATOR;
import static com.fort.consts.CommonConstants.DATA_SUFFIX;

import java.sql.Date;
import java.util.Calendar;

import com.fort.util.DateUtils;

public class Patient {
	
	private final String title;
	private final String fname;
	private final Date dob;
	private final String gender;
	private final String mname;
	private final String lname;
	private final String address;
	private final String city;
	private final String state;
	private final String zip_cd;
	private final String phone;
	private final String email;
	private final Integer patId;
	private final Integer age; //calculated
	// added by rahul for patient center
	private final String ins_carrier;
	private final String ins_groupNumber;
	private final String ins_memberNumber;
	private final String ins_subscriber;
	private final String ins_relationship;
	private final String emrginfo_name;
	private final String emrginfo_relation;
	private final String emrginfo_phone;
	private final String emrginfo_email;
	
	private final String maritalStat;
	private final String religion;
	private final String diet;
	private final String profession;
	private final String income;
	
	
	

	public Patient(String title, String fname, String mname, String lname,Date dob, String gender
			, String address, String city,
			String state, String zip_cd, String phone, String email,
			 Integer age, String ins_carrier,
			String ins_groupNumber, String ins_memberNumber,
			String ins_subscriber, String ins_relationship,
			String emrginfo_name, String emrginfo_relation,
			String emrginfo_phone, String emrginfo_email, String maritalStat,
			String religion, String diet, String profession, String income,Integer patId) {
		super();
		this.title = title;
		this.fname = fname;
		this.dob = dob;
		this.gender = gender;
		this.mname = mname;
		this.lname = lname;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip_cd = zip_cd;
		this.phone = phone;
		this.email = email;
		this.patId = patId;
		if( ( age == null || age == 0 ) && dob != null ) {
			this.age=DateUtils.getAgeFromDob(dob);
		}else{
			this.age = age;
		}
		this.ins_carrier = ins_carrier;
		this.ins_groupNumber = ins_groupNumber;
		this.ins_memberNumber = ins_memberNumber;
		this.ins_subscriber = ins_subscriber;
		this.ins_relationship = ins_relationship;
		this.emrginfo_name = emrginfo_name;
		this.emrginfo_relation = emrginfo_relation;
		this.emrginfo_phone = emrginfo_phone;
		this.emrginfo_email = emrginfo_email;
		this.maritalStat = maritalStat;
		this.religion = religion;
		this.diet = diet;
		this.profession = profession;
		this.income = income;
		if(dob != null){
			age= DateUtils.getDiffYears(dob, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		} else {
			age=null;
		}
	}

	public String patientName(){
		return title + " " + fname + " " + lname;
	}

	public String getIns_carrier() {
		return ins_carrier;
	}


	public String getIns_groupNumber() {
		return ins_groupNumber;
	}


	public String getIns_memberNumber() {
		return ins_memberNumber;
	}


	public String getIns_subscriber() {
		return ins_subscriber;
	}


	public String getIns_relationship() {
		return ins_relationship;
	}


	public String getEmrginfo_name() {
		return emrginfo_name;
	}


	public String getEmrginfo_relation() {
		return emrginfo_relation;
	}


	public String getEmrginfo_phone() {
		return emrginfo_phone;
	}


	public String getEmrginfo_email() {
		return emrginfo_email;
	}


	public String getMaritalStat() {
		return maritalStat;
	}


	public String getReligion() {
		return religion;
	}


	public String getDiet() {
		return diet;
	}


	public String getProfession() {
		return profession;
	}


	public String getIncome() {
		return income;
	}


	public Patient(Integer patId) {
		this(null, null, null, null,null, null,null, null, null, null,null, null, null, null, null,null, null, null, null, null, null, null, null, null, null, null, null, patId);
	}

	
	public String toDataTableRow(){
		return toString(DATA_PREFIX, DATA_SEPERATOR, DATA_SUFFIX);
	}
	//don't change the logic of this function.. it will break jsps.
	public String toString( final String prefix, final String sep, final String suffix ){
		StringBuilder s = new StringBuilder();
		return s.append(prefix).append(fname).append(sep).append(lname).append(sep).append(dob).append(sep).append(patId).append(sep).append(phone).append(sep).append(email).append(suffix).toString();
	}

	@Override
	public String toString() {
		return String.format("Patient [title=%s, fname=%s, dob=%s, gender=%s, mname=%s, lname=%s, address=%s, city=%s, state=%s, zip_cd=%s, phone=%s, email=%s, patId=%s, age=%s]",
						title, fname, dob, gender, mname, lname, address, city, state, zip_cd, phone, email, patId, age);
	}

	public String getTitle() {
		return title;
	}

	public String getFname() {
		return fname;
	}

	public Date getDob() {
		return dob;
	}

	public String getGender() {
		return gender;
	}

	public String getMname() {
		return mname;
	}

	public String getLname() {
		return lname;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip_cd() {
		return zip_cd;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}
	public Integer getPatId() {
		return patId;
	}

	public Integer getAge() {
		return age;
	}
}
