package com.fort.tos;

import java.sql.Date;

public class Encounter {

	private int pat_id;
	private int doc_id;
	private Date encounter_dt;
	private String doc_name;
	private String app_slot;
	private String pat_name;
	private String age;
	private String pat_gender;
	private String res_visit;
	private String status;
	private String type;
	
	@Override
	public String toString() {
		return String
				.format("Encounter [pat_id=%s, doc_id=%s, encounter_dt=%s, doc_name=%s, app_slot=%s, pat_name=%s, age=%s, pat_gender=%s, res_visit=%s, status=%s, type=%s]",
						pat_id, doc_id, encounter_dt, doc_name, app_slot,
						pat_name, age, pat_gender, res_visit, status, type);
	}
	public Encounter(int pat_id, int doc_id, Date encounter_dt,
			String doc_name, String app_slot, String pat_name, String age,
			String pat_gender, String res_visit, String status, String type) {
		super();
		this.pat_id = pat_id;
		this.doc_id = doc_id;
		this.encounter_dt = encounter_dt;
		this.doc_name = doc_name;
		this.app_slot = app_slot;
		this.pat_name = pat_name;
		this.age = age;
		this.pat_gender = pat_gender;
		this.res_visit = res_visit;
		this.status = status;
		this.type = type;
	}

	
	public String getApp_slot() {
		return app_slot;
	}
	public void setApp_slot(String app_slot) {
		this.app_slot = app_slot;
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
	public String getDoc_name() {
		return doc_name;
	}
	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}
	public String getPat_name() {
		return pat_name;
	}
	public void setPat_name(String pat_name) {
		this.pat_name = pat_name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPat_gender() {
		return pat_gender;
	}
	public void setPat_gender(String pat_gender) {
		this.pat_gender = pat_gender;
	}
	public String getRes_visit() {
		return res_visit;
	}
	public void setRes_visit(String res_visit) {
		this.res_visit = res_visit;
	}
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
	
	

}
