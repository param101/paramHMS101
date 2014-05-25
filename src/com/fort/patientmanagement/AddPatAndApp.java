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

public class AddPatAndApp extends BaseAction {
	private Date app_dt;
	private String title;
	private String fname;
	private Date dob;
	private String gender;
	private String time;
	private String mname;
	private String lname;

	private String reason_visit;
	private int doc_id;


	private String time_slot;
	private String payment_method;
	private String deposit;
	private String address;
	private String city;
	private String state;
	private String zip_cd;
	private String phone;
	private String email;
	
	private Integer patId;

	public Integer getPatId() {
		return patId;
	}

	public void setPatId(Integer patId) {
		this.patId = patId;
	}

	@Override
	public String doExecute(){
		logInfo(this);
			
		System.out.println("+++++++++++++++++++++++++++++++++patiid"+patId);
		if(patId==null || patId==0)
		{
		 Patient p = new Patient(title,fname,mname,lname,dob,gender,address,city,state,zip_cd,phone,email,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null, null);
		
		 
		try {
			
			int pid=PatientDao.getDaoInst().insertNewPatient(p,user.getuId());
			Appointment appointment;
			logInfo(p);
			logInfo(p.getPatId());
			logInfo(doc_id);
			//appointment = new Appointment(pid, Integer.parseInt(doc_name),doc_name, app_dt,time_slot, reason_visit,"tobedone","tobedone");
			appointment = new Appointment(pid, doc_id, app_dt, time_slot, reason_visit, "tobedone", "tobedone", p.patientName(), p.getAge(), p.getGender(), null); 
			AppointmentDao.getDaoInst().insertNewAppointment(appointment,user.getuId());
			
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			
			session.setAttribute("PatId",pid);	
			session.setAttribute("Appdt_Slot", app_dt+" Time: "+time_slot);
			
			
		} 
		catch(Exception e)
		{
			
			e.printStackTrace();
			return "error";
		}
		return "pat_added_success";
	}
		Patient pat;
		try {
			pat = PatientDao.getDaoInst().findPatientByPatId(patId);
		
		} catch (BusinessException e) {
			addErrToReq("Error occured while retrieving Patient info by PatId: "+ e.getMessage());
			e.printStackTrace();
			return "error";
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
			return "error";
		}
		super.getSession().put("patientbean", pat );
		return "add_app_existing";
	}

	/*private boolean validate() {
		boolean e = true;
		if( ! Validations.validateUserId(userid)){
			addErrToReq("User ID is invalid");
			e = false;
		}
		if( !Validations.validatePwd(pwd) || !Validations.validatePwd(cpwd)){
			addErrToReq("Password is not per out policy (minlength:"+CommonConstants.PWD_LEN_MIN+", maxlength:"+CommonConstants.PWD_LEN_MAX+")");
			if(!pwd.equals(cpwd)){
				addErrToReq("password & confirm password don't match");
			}
			e = false;
		}
		if( !Validations.validateEmail(email)){
			addErrToReq("Email ID is not per out policy (minlength:"+CommonConstants.EMAIL_LEN_MIN+", maxlength:"+CommonConstants.EMAIL_LEN_MAX+")");
			e = false;
		}
		try{
			int r = Integer.parseInt(role);
			if( !Validations.validateRole(r)){
				addErrToReq("Role ID is not valid. Please try again. If problem persists, please contact your admin" );
				e = false;
			}
		} catch( NumberFormatException n){
			addErrToReq("Perhaps you didn't select a role for the new user. Please try again. If problem persists, please contact your admin" );
			e = false;
		}	
		return e;
	}
*/
	

	/*@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CreateNewUserAction [userid=").append(userid)
				.append(", name=").append(name).append(", email=")
				.append(email).append(", pwd=").append(pwd).append(", cpwd=")
				.append(cpwd).append(", role=").append(role).append("]");
		return builder.toString();
	}*/


	

	

		public Date getApp_dt() {
		return app_dt;
	}

	public void setApp_dt(Date app_dt) {
		this.app_dt = app_dt;
	}

		@Override
	protected String getCurrentFunctionName() {
		return "Patient Management";
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

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
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

	
		public String getReason_visit() {
			return reason_visit;
		}

		public void setReason_visit(String reason_visit) {
			this.reason_visit = reason_visit;
		}

		

		public String getPayment_method() {
			return payment_method;
		}

		public void setPayment_method(String payment_method) {
			this.payment_method = payment_method;
		}

		public String getDeposit() {
			return deposit;
		}

		public void setDeposit(String deposit) {
			this.deposit = deposit;
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
		
		public int getDoc_id() {
			return doc_id;
		}

		public void setDoc_id(int doc_id) {
			this.doc_id = doc_id;
		}

		public String getTime_slot() {
			return time_slot;
		}

		public void setTime_slot(String time_slot) {
			this.time_slot = time_slot;
		}
}
