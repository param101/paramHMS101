package com.fort.tos;

import java.sql.Date;

public class RoomMgmt {
	
	private int pat_id;
	
	private Date dt_admission;
	private String res_visit;
	private String room_type;
	private String room_num;
	private String bed_num;
	private int id;
	public int getPat_id() {
		return pat_id;
	}
	public void setPat_id(int pat_id) {
		this.pat_id = pat_id;
	}
	public Date getDt_admission() {
		return dt_admission;
	}
	public void setDt_admission(Date dt_admission) {
		this.dt_admission = dt_admission;
	}
	public String getRes_visit() {
		return res_visit;
	}
	public void setRes_visit(String res_visit) {
		this.res_visit = res_visit;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public RoomMgmt(int pat_id, Date dt_admission, String res_visit,
			String room_type, String room_num, String bed_num) {
		super();
		this.pat_id = pat_id;
		this.dt_admission = dt_admission;
		this.res_visit = res_visit;
		this.room_type = room_type;
		this.room_num = room_num;
		this.bed_num = bed_num;
	}
}