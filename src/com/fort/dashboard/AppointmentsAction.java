package com.fort.dashboard;

import static com.fort.consts.CommonConstants.ACTION_TYPE_REFRESH;
import static com.fort.consts.CommonConstants.ACTION_TYPE_SAVE;
import static com.fort.consts.CommonConstants.ACTION_TYPE_DELETE;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fort.bean.DashBoardOutPatBean;
import com.fort.common.exception.SystemException;
import com.fort.dao.AppointmentDao;
import com.fort.dao.DashboardDao;
import com.fort.sec.action.BaseAction;
import com.fort.tos.Appointment;
import com.fort.util.JSONObjectFort;

public class AppointmentsAction extends BaseAction {

	private int row;
	private String appointmentTime;
	private int doctorId;
	private String patName;
	private int age;
	private String gender;
	private String reasonForVisit;
	private String status;
	private String billing;
	private String actionType;
	private Date date;
	
	@Override
	public String doExecute() throws SystemException {
		logInfo(this);
		if(actionType.equalsIgnoreCase(ACTION_TYPE_REFRESH)){
			if(date == null )
				date= new Date(Calendar.getInstance().getTime().getTime());
			List<DashBoardOutPatBean> data = DashboardDao.getDaoInst().getDashboardOutPatList(date);
			logInfo( "inside DashboardAction: ret from dao: " + data );
			getSession().put("DashboardOutPatList", data);
			getRequest().put("date", date);
			
			JSONObject jsonObj = new JSONObjectFort();
			JSONArray jsonArray = new JSONArray();
			try {
				if(data != null) {
					int i = 1;
					for(DashBoardOutPatBean bean : data){
						JSONObject json = new JSONObjectFort();
						json.put("row", bean.getAppointmentId());
						json.put("appointmentTime", bean.getApp_slots());
						json.put("doctorId", bean.getDocName());
						json.put("patName", bean.getPatName());
						json.put("age",bean.getAge());
						json.put("gender", bean.getGender());
						json.put("reasonForVisit", bean.getReasonForVisit());
						json.put("status", bean.getStatus());
						json.put("billing", "NA");
						json.put("edit", "");//keep this guy empty. required in jsp
						json.put("patId", bean.getPatId());
						jsonArray.put(json);
					}
				}
				jsonObj.put("aaData", jsonArray);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new SystemException(e.getMessage());
			}
			getRequest().put("jsonData", jsonObj);
			logInfo("jsonArray: "+jsonObj.toString());
			return "json-data";
		} else if(actionType.equalsIgnoreCase(ACTION_TYPE_SAVE)){
			logInfo("save called......................" + this);
			AppointmentDao.getDaoInst().updateAppointment(new Appointment(0, doctorId, date, appointmentTime, reasonForVisit, status, billing, patName, age, gender, row), getUser().getuId());
		} else if(actionType.equalsIgnoreCase(ACTION_TYPE_DELETE)){
			logInfo("delete called....."+this);
			AppointmentDao.getDaoInst().deleteAppointment(row);
		}
		return null;
	}

	@Override
	protected String getCurrentFunctionName() {
		return "Dashboard";
	}

	@Override
	public String toString() {
		return String.format("AppointmentsAction [row=%s, appointmentTime=%s, doctorName=%s, patName=%s, age=%s, gender=%s, reasonForVisit=%s, status=%s, billing=%s, actionType=%s, date=%s]", 
				row, appointmentTime, doctorId, patName, age, gender,	reasonForVisit, status, billing, actionType, date);
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getPatName() {
		return patName;
	}

	public void setPatName(String patName) {
		this.patName = patName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getReasonForVisit() {
		return reasonForVisit;
	}

	public void setReasonForVisit(String reasonForVisit) {
		this.reasonForVisit = reasonForVisit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBilling() {
		return billing;
	}

	public void setBilling(String billing) {
		this.billing = billing;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


}
