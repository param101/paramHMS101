package com.fort.emr.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class EMRObjectiveVitalsBean extends EMRTableBean {
	private String time;
	private String heigth;
	private String weigth;
	private String bmi;
	private String temperature;
	private String hr;
	private String bloodPressure;
	private String rr;
	private String o2;
	private String visionL;
	private String visionR;
	private String hearingL;
	private String hearingR;
	
	public EMRObjectiveVitalsBean(Integer encounterId, Integer row, String actionType, String time, String heigth, String weigth, String bmi, String temperature, String hr, String bloodPressure, String rr, String o2, String visionL, String visionR, String hearingL, String hearingR) {
		super(encounterId, row, actionType);
		this.time = time;
		this.heigth = heigth;
		this.weigth = weigth;
		this.bmi = bmi;
		this.temperature = temperature;
		this.hr = hr;
		this.bloodPressure = bloodPressure;
		this.rr = rr;
		this.o2 = o2;
		this.visionL = visionL;
		this.visionR = visionR;
		this.hearingL = hearingL;
		this.hearingR = hearingR;
	}

	@Override
	public boolean isInsertSupported() {
		return true;
	}

	@Override
	public boolean isDeleteSupported() {
		return true;
	}

	@Override
	public boolean isUpdateSupported() {
		return true;
	}

	@Override
	public QueryParams insertQuery(String updateUser) {
		return new QueryParams( "insert into emr_objective_vitals ( encounter_id, row, time, heigth, weigth, bmi, temperature, hr, bloodPressure, rr, o2, visionL, visionR, hearingL, hearingR, create_u, create_t) " +
				" values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now())", 
				new Object[]{getEncounterId(), getRow(), getTime(), getHeigth(), getWeigth(), getBmi(), getTemperature(), getHr(), getBloodPressure(), getRr(), getO2(), getVisionL(), getVisionR(), getHearingL(), getHearingR(), updateUser});
	}

	@Override
	public QueryParams updateQuery(String updateUser) {
		return new QueryParams("update emr_objective_vitals set time=?, heigth=?, weigth=?, bmi=?, temperature=?, hr=?, bloodPressure=?, rr=?, o2=?, visionL=?, visionR=?, hearingL=?, hearingR=?, update_u=?, update_t=now() where encounter_id=? and row=?", 
				new Object[]{getTime(), getHeigth(), getWeigth(), getBmi(), getTemperature(), getHr(), getBloodPressure(), getRr(), getO2(), getVisionL(), getVisionR(), getHearingL(), getHearingR(), updateUser, getEncounterId(), getRow()});
	}

	@Override
	public QueryParams refreshQuery() {
		return new QueryParams("select encounter_id, row, time, heigth, weigth, bmi, temperature, hr, bloodPressure, rr, o2, visionL, visionR, hearingL, hearingR from emr_objective_vitals where encounter_id=?", new Object[]{getEncounterId()});
	}

	@Override
	public String getTableName() {
		return "emr_objective_vitals";
	}

	@Override
	public EMRTableBean getBeanFromResultSet(ResultSet rs) throws SQLException {
		return new EMRObjectiveVitalsBean(rs.getInt("encounter_id"), rs.getInt("row"), actionType, rs.getString("time"), rs.getString("heigth"), rs.getString("weigth"), rs.getString("bmi"), rs.getString("temperature"), rs.getString("hr"), rs.getString("bloodPressure"), rs.getString("rr"), rs.getString("o2"), rs.getString("visionL"), rs.getString("visionR"), rs.getString("hearingL"), rs.getString("hearingR"));
	}

	@Override
	public void addDataToJson(JSONObject json) throws JSONException {
		json.put("time", getTime());
		json.put("heigth", getHeigth());
		json.put("weigth", getWeigth());
		json.put("bmi", getBmi());
		json.put("temperature", getTemperature());
		json.put("hr", getHr());
		json.put("bloodPressure", getBloodPressure());
		json.put("rr", getRr());
		json.put("o2", getO2());
		json.put("visionL", getVisionL());
		json.put("visionR", getVisionR());
		json.put("hearingL", getHearingL());
		json.put("hearingR", getHearingR());
	}

	@Override
	public String toString() {
		return String.format("EMRObjectiveVitalsBean [time=%s, heigth=%s, weigth=%s, bmi=%s, temperature=%s, hr=%s, bloodPressure=%s, rr=%s, o2=%s, visionL=%s, visionR=%s, hearingL=%s, hearingR=%s, encounterId=%s, row=%s, actionType=%s]", time,
				heigth, weigth, bmi, temperature, hr, bloodPressure, rr, o2, visionL, visionR, hearingL, hearingR, encounterId, row, actionType);
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getHeigth() {
		return heigth;
	}

	public void setHeigth(String heigth) {
		this.heigth = heigth;
	}

	public String getWeigth() {
		return weigth;
	}

	public void setWeigth(String weigth) {
		this.weigth = weigth;
	}

	public String getBmi() {
		return bmi;
	}

	public void setBmi(String bmi) {
		this.bmi = bmi;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHr() {
		return hr;
	}

	public void setHr(String hr) {
		this.hr = hr;
	}

	public String getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public String getRr() {
		return rr;
	}

	public void setRr(String rr) {
		this.rr = rr;
	}

	public String getO2() {
		return o2;
	}

	public void setO2(String o2) {
		this.o2 = o2;
	}

	public String getVisionL() {
		return visionL;
	}

	public void setVisionL(String visionL) {
		this.visionL = visionL;
	}

	public String getVisionR() {
		return visionR;
	}

	public void setVisionR(String visionR) {
		this.visionR = visionR;
	}

	public String getHearingL() {
		return hearingL;
	}

	public void setHearingL(String hearingL) {
		this.hearingL = hearingL;
	}

	public String getHearingR() {
		return hearingR;
	}

	public void setHearingR(String hearingR) {
		this.hearingR = hearingR;
	}

}
