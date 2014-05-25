package com.fort.emr;

import com.fort.emr.bean.EMRObjectiveVitalsBean;
import com.fort.emr.bean.EMRTableBean;

public class EMRObjectiveVitalsAction extends EMRBaseAction {

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

	@Override
	protected boolean validateBean() {
		return true;
	}

	@Override
	protected EMRTableBean getBean() {
		return new EMRObjectiveVitalsBean(encounterId, row, actionType, time, heigth, weigth, bmi, temperature, hr, bloodPressure, rr, o2, visionL, visionR, hearingL, hearingR);
	}

	@Override
	public String toString() {
		return String.format(
				"EMRObjectiveVitalsAction [time=%s, heigth=%s, weigth=%s, bmi=%s, temperature=%s, hr=%s, bloodPressure=%s, rr=%s, o2=%s, visionL=%s, visionR=%s, hearingL=%s, hearingR=%s, row=%s, encounterId=%s, actionType=%s, isLocked=%s, user=%s]",
				time, heigth, weigth, bmi, temperature, hr, bloodPressure, rr, o2, visionL, visionR, hearingL, hearingR, row, encounterId, actionType, isLocked, user);
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