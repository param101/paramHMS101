package com.fort.tos;


public class AppSlots {
	
	private final String slot_during;
	private final String app_slots;
	private final Integer id;

	
	 public Integer getId() {
		return id;
	}
	public String getSlot_during() {
		return slot_during;
	}
	public String getApp_slots() {
		return app_slots;
	}

	 public AppSlots(Integer id, String slot_during, String app_slots) {
		super();
		this.id = id;
		this.slot_during = slot_during;
		this.app_slots = app_slots;
	}
	
}
