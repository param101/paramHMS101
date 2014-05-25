package com.fort.tos;

import java.sql.Time;

public class Doctor {
	
	 private final int doc_id	  ;
	 private final String doc_dept 	  ;
	 private final Time doc_dob 	  ;
	 private final String doc_m_nm	  ;
	 private final String doc_l_nm	  ;
	 private final int active;
	 private final String doc_add	  ;
	 private final String doc_city	  ;
	 private final String doc_state	  ;
	 private final String doc_phone	  ;
	 private final String doc_email	  ;
		private final String doc_first_n ;
	
	
	
	 @Override
	public String toString() {
		return "Doctor [doc_id=" + doc_id + ", doc_first_n=" + doc_first_n
				+ ", doc_dept=" + doc_dept + ", doc_dob=" + doc_dob
				+ ", doc_m_nm=" + doc_m_nm + ", doc_l_nm=" + doc_l_nm
				+ ", doc_status=" + active + ", doc_add=" + doc_add
				+ ", doc_city=" + doc_city + ", doc_state=" + doc_state
				+ ", doc_phone=" + doc_phone + ", doc_email=" + doc_email + "]";
	}

	 public Doctor(int doc_id, String doc_first_n, String doc_dept,
			Time doc_dob, String doc_m_nm, String doc_l_nm,
			int active, String doc_add, String doc_city,
			String doc_state, String doc_phone, String doc_email) {
		super();
		this.doc_id = doc_id;
		this.doc_first_n = doc_first_n;
		this.doc_dept = doc_dept;
		this.doc_dob = doc_dob;
		this.doc_m_nm = doc_m_nm;
		this.doc_l_nm = doc_l_nm;
		this.active = active;
		this.doc_add = doc_add;
		this.doc_city = doc_city;
		this.doc_state = doc_state;
		this.doc_phone = doc_phone;
		this.doc_email = doc_email;
	}
	
	 
	 
	 
	 public int getDoc_id() {
		return doc_id;
	}
	public String getDoc_first_n() {
		return doc_first_n;
	}
	public String getDoc_dept() {
		return doc_dept;
	}
	public Time getDoc_dob() {
		return doc_dob;
	}
	public String getDoc_m_nm() {
		return doc_m_nm;
	}
	public String getDoc_l_nm() {
		return doc_l_nm;
	}
	public int getDoc_status() {
		return active;
	}
	public String getDoc_add() {
		return doc_add;
	}
	public String getDoc_city() {
		return doc_city;
	}
	public String getDoc_state() {
		return doc_state;
	}
	public String getDoc_phone() {
		return doc_phone;
	}
	public String getDoc_email() {
		return doc_email;
	}
	
	 
	 

}
