package com.fort.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fort.common.exception.SystemException;
import com.fort.tos.AppSlots;

public class AppSlotsDao extends BaseDao {

	private int id;
	private String slot_during;
	private String app_slots;

	public AppSlotsDao(int id, String slot_during, String app_slots) {
		super();
		this.id = id;
		this.slot_during = slot_during;
		this.app_slots = app_slots;
	}
	private AppSlotsDao(){};
	private static AppSlotsDao _inst = new AppSlotsDao();
	public static AppSlotsDao getDaoInst(){return _inst;}

	public List<String> getSlots() throws SystemException {
		List<String> listSlots = new ArrayList<String>();
		List<AppSlots> slots = getAppSlots();
		for(AppSlots slot: slots){
			listSlots.add(slot.getApp_slots());
		}
		return listSlots;
	}

	public List<AppSlots> getAppSlots() throws SystemException{
		List<AppSlots> lSlots = (List<AppSlots>) getListByCriteria("select * from app_slots", EMPTY_OBJECT_ARRAY);
		return lSlots;
	}
	public int getId() {
		return id;
	}

	public String getSlot_during() {
		return slot_during;
	}

	public String getApp_slots() {
		return app_slots;
	}

	public Object toDataObject(ResultSet rs) throws SQLException {
		return new AppSlots(rs.getInt("id"), rs.getString("slot_during"), rs.getString("app_slots"));
	}
}