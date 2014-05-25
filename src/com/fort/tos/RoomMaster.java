package com.fort.tos;

public class RoomMaster {
	
	private int id;
	private String room_type;
	private String room_num;
	private String bed_num;
	private int is_avail;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getIs_avail() {
		return is_avail;
	}
	public void setIs_avail(int is_avail) {
		this.is_avail = is_avail;
	}
	public RoomMaster(int id, String room_type, String room_num,
			String bed_num, int is_avail) {
		super();
		this.id = id;
		this.room_type = room_type;
		this.room_num = room_num;
		this.bed_num = bed_num;
		this.is_avail = is_avail;
	}
	
}
