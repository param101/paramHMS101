package com.fort.patientmanagement;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.fort.common.exception.BusinessException;
import com.fort.common.exception.SystemException;
import com.fort.dao.AppointmentDao;
import com.fort.dao.PatientDao;
import com.fort.sec.action.BaseAction;
import com.fort.tos.Appointment;
import com.fort.tos.Patient;

public class AddAppExistingPatientAction extends BaseAction {
	private int patId;
	private int doc_id;
	//private String doc_name;
	private Date encounter_dt;
	private String app_slot;
	private String reason_visit;

	@Override
	public String doExecute() {
		logInfo(this);
		try {
			Patient pat = PatientDao.getDaoInst().findPatientByPatId(patId);
			// Appointment app=new
			// Appointment(patId,Integer.parseInt(doc_name),doc_name,encounter_dt,
			// app_slot, reason_visit,"tobedone","tobediscussed");
			Appointment app = new Appointment(patId, doc_id, encounter_dt, app_slot, reason_visit, "tobedone", "tobedone", pat.patientName(), pat.getAge(), pat.getGender(), null);
			AppointmentDao.getDaoInst().insertNewAppointment(app, user.getuId());
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();

			session.setAttribute("PatId", patId);
			session.setAttribute("Appdt_Slot", encounter_dt + " Time: " + app_slot);

		} catch (SystemException e) {

			e.printStackTrace();
			return "error";
		} catch (BusinessException e) {
			e.printStackTrace();
			return "error";
		}
		return "success";

	}

	@Override
	public String toString() {
		return String.format("AddAppExistingPatientAction [patId=%s, doc_id=%s, encounter_dt=%s, app_slot=%s, reason_visit=%s]", patId, doc_id, encounter_dt, app_slot, reason_visit);
	}

	public int getPatId() {
		return patId;
	}

	public void setPatId(int patId) {
		this.patId = patId;
	}

//	public String getDoc_name() {
//		return doc_name;
//	}

//	public void setDoc_name(String doc_name) {
//		this.doc_name = doc_name;
//	}

	public String getReason_visit() {
		return reason_visit;
	}

	public void setReason_visit(String reason_visit) {
		this.reason_visit = reason_visit;
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

	@Override
	protected String getCurrentFunctionName() {
		return "Patient Management";
	}
}
