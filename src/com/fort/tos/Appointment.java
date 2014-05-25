package com.fort.tos;

import java.sql.Date;

public class Appointment {
	
	public Appointment(int pat_id,int doc_id, Date encounter_dt, String app_slot, String res_visit,String status,String type, String pat_name, int age, String pat_gender, Integer app_id ) {
		this.pat_id = pat_id;
		this.doc_id = doc_id;
		//this.doc_name=doc_name;
		this.encounter_dt = encounter_dt;
		this.app_slot = app_slot;
		this.res_visit = res_visit;
		this.status=status;
		this.type=type;
		this.pat_name=pat_name;
		this.age=age;
		this.pat_gender=pat_gender;
		this.app_id = app_id;
	}
	
	private  int pat_id	  ;
	private  int doc_id	  ;
	private Date encounter_dt;
	private String app_slot	  ;
	private  String res_visit	  ;
	private String status;
	private String type;
	//private String doc_name;
	private String pat_name;
	private int age;
	private String pat_gender;
	private Integer app_id;
	
	@Override
	public String toString() {
		return String.format("Appointment [pat_id=%s, doc_id=%s, encounter_dt=%s, app_slot=%s, res_visit=%s, status=%s, type=%s, doc_name=%s, pat_name=%s, age=%s, pat_gender=%s, app_id=%s]", 
				pat_id, doc_id, encounter_dt, app_slot, res_visit, status, type, pat_name, age, pat_gender, app_id);
	}
	
//	public String getDoc_name() {
//		return doc_name;
//	}
//	public void setDoc_name(String doc_name) {
//		this.doc_name = doc_name;
//	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPat_id() {
		return pat_id;
	}
	public void setPat_id(int pat_id) {
		this.pat_id = pat_id;
	}
	public int getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(int doc_id) {
		this.doc_id = doc_id;
	}
	public Date getEncounter_dt() {
		return encounter_dt;
	}
	public void setEncounter_dt(Date encounter_dt) {
		this.encounter_dt = encounter_dt;
	}
	public String getApp_slot() {
		return app_slot;
	}
	public void setApp_slot(String app_slot) {
		this.app_slot = app_slot;
	}
	public String getRes_visit() {
		return res_visit;
	}
	public void setRes_visit(String res_visit) {
		this.res_visit = res_visit;
	}
	public String getPat_name() {
		return pat_name;
	}
	public void setPat_name(String pat_name) {
		this.pat_name = pat_name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPat_gender() {
		return pat_gender;
	}
	public void setPat_gender(String pat_gender) {
		this.pat_gender = pat_gender;
	}
	public Integer getApp_id() {
		return app_id;
	}
	public void setApp_id(Integer app_id) {
		this.app_id = app_id;
	}
}
