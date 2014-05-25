package com.fort.roommgmt;

import java.util.List;

import com.fort.common.exception.SystemException;
import com.fort.dao.RoomMgmtDao;

public class RoomManagement {

	/**
	 * @param args
	 */
	
	
	public List<String> getAvailRoomTypes() throws SystemException {
		return RoomMgmtDao.getDaoInst().getAvailRoomTypes();
	}
	public List<String> getAvailRooms(String room_type) throws SystemException
	{
		return RoomMgmtDao.getDaoInst().getAvailRooms(room_type);
	}
	public List<String> getAvailBeds(String room_type, String room_num) throws SystemException {
		// TODO Auto-generated method stub
		return RoomMgmtDao.getDaoInst().getAvailBeds(room_type,room_num);
	}
	
	

}
