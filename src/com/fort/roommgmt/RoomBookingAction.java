package com.fort.roommgmt;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.fort.common.exception.SystemException;
import com.fort.dao.AppointmentDao;
import com.fort.dao.RoomMgmtDao;
import com.fort.sec.action.BaseAction;
import com.fort.tos.Appointment;
import com.fort.tos.RoomMgmt;

public class RoomBookingAction extends BaseAction {
	private Integer patId;
	private String fname;
	private String lname;
    private Date dt_admission;
	private String room_type;
	private String room_num;
	private String bed_num;
	private String res_visit;
	
	@Override
	
	public String doExecute() {
		
		RoomMgmt rmgmt = new RoomMgmt(patId, dt_admission, res_visit, room_type, room_num, bed_num);
		try {
			
			System.out.println(this);
			
			RoomMgmtDao.getDaoInst().insertRoomBooking(rmgmt,user.getuId());
			RoomMgmtDao.getDaoInst().updateRoomMaster(rmgmt,user.getuId());
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();

			session.setAttribute("PatId", patId);
			session.setAttribute("room_loc",room_type +":"+room_num+":"+bed_num);

		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		
		return "success";
		
	}



	@Override
	public String toString() {
		return String
				.format("RoomBookingAction [patId=%s, fname=%s, lname=%s, dt_admission=%s, room_type=%s, room_num=%s, bed_num=%s, res_visit=%s]",
						patId, fname, lname, dt_admission, room_type, room_num,
						bed_num, res_visit);
	}






	public Integer getPatId() {
		return patId;
	}



	public void setPatId(Integer patId) {
		this.patId = patId;
	}



	public String getRes_visit() {
		return res_visit;
	}



	public void setRes_visit(String res_visit) {
		this.res_visit = res_visit;
	}



	public String getFname() {
		return fname;
	}



	public void setFname(String fname) {
		this.fname = fname;
	}



	public String getLname() {
		return lname;
	}



	public void setLname(String lname) {
		this.lname = lname;
	}



	public Date getDt_admission() {
		return dt_admission;
	}



	public void setDt_admission(Date dt_admission) {
		this.dt_admission = dt_admission;
	}



	public String getRoom_type() {
		return room_type;
	}



	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}



	public String getRoom_num() {
		return room_num;
	}



	public void setRoom_num(String room_num) {
		this.room_num = room_num;
	}



	public String getBed_num() {
		return bed_num;
	}



	public void setBed_num(String bed_num) {
		this.bed_num = bed_num;
	}



	@Override
	protected String getCurrentFunctionName() {
		return "Room Management";
	}
}
