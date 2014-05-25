package com.fort.bean;


public class DashBoardOutPatBean {
	
	
	private final String app_slots;
	public DashBoardOutPatBean(String app_slots, String docName, String patName, short age, String gender, String reasonForVisit, String room, String status, boolean billing, int appointmentId, Integer patId) {
		super();
		this.app_slots = app_slots;
		this.docName = docName;
		this.patName = patName;
		this.age = age;
		this.gender = gender;
		this.reasonForVisit = reasonForVisit;
		this.room = room;
		this.status = status;
		this.billing = billing;
		this.appointmentId =  appointmentId;
		this.patId=patId;
	}
	
	private final Integer appointmentId;
	private final String docName;
	private final String patName;
	private final short age;
	private final String gender;
	private final String reasonForVisit;
	private final String room;
	private final String status;
	private final boolean billing;
	private final Integer patId;
	
	@Override
	public String toString() {
		return String.format("DashBoardOutPatBean [app_slots=%s, appointmentId=%s, docName=%s, patName=%s, age=%s, gender=%s, reasonForVisit=%s, room=%s, status=%s, billing=%s, patId=%s]", 
				app_slots, appointmentId, docName, patName, age, gender, reasonForVisit, room, status, billing, patId);
	}
	
	public String getApp_slots() {
		return app_slots;
	}
	public String getDocName() {
		return docName;
	}
	public String getPatName() {
		return patName;
	}
	public short getAge() {
		return age;
	}
	public String getGender() {
		return gender;
	}
	public String getReasonForVisit() {
		return reasonForVisit;
	}
	public String getRoom() {
		return room;
	}
	public String getStatus() {
		return status;
	}
	public boolean isBilling() {
		return billing;
	}
	public Integer getAppointmentId() {
		return appointmentId;
	}
	public Integer getPatId() {
		return patId;
	}
}
