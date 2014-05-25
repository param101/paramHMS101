package com.fort.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fort.common.exception.SystemException;
import com.fort.tos.RoomMaster;
import com.fort.tos.RoomMgmt;

public class RoomMgmtDao extends BaseDao{

	private final static RoomMgmtDao _inst = new RoomMgmtDao();

	private RoomMgmtDao() {
	};

	public static RoomMgmtDao getDaoInst() {
		return _inst;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getAvailRoomTypes() throws SystemException {
		try {
		
			List<RoomMaster> lR = (List<RoomMaster>) getListByCriteria("select * from room_master where is_avail=1 order by room_type", EMPTY_OBJECT_ARRAY);
			
			List<String> lRoomTypes=new ArrayList<String>();

			if(lR!=null && lR.size()>0)
			{
			Iterator<RoomMaster> iR = lR.iterator();
			
			while (iR.hasNext()) {
				RoomMaster dr = iR.next();
				if(!lRoomTypes.contains((String)dr.getRoom_type()))
				lRoomTypes.add(dr.getRoom_type());
			}
			System.out.println(lRoomTypes);
			}
			return lRoomTypes;
			
		} catch (SystemException e) {
			e.printStackTrace();
			throw e;
		}

	}
	
	@SuppressWarnings("unchecked")
	public List<String> getAvailRooms(String room_type)  throws SystemException{
		try {
			
			Object[] params = new Object[]{room_type};
			List<RoomMaster> lR = (List<RoomMaster>) getListByCriteria("select * from room_master where is_avail=1 and room_type=?", params);
			
		
			List<String> lRoomNumbers=new ArrayList<String>();

			if(lR!=null && lR.size()>0)
			{
			Iterator<RoomMaster> iR = lR.iterator();
			
			while (iR.hasNext()) {
				RoomMaster dr = iR.next();
				if(!lRoomNumbers.contains((String)dr.getRoom_num()))
					lRoomNumbers.add(dr.getRoom_num());
			}
			System.out.println(lRoomNumbers);
			}
			return lRoomNumbers;
			
		} catch (SystemException e) {
			e.printStackTrace();
			throw e;
		}

	}



	public Object toDataObject(ResultSet rs) throws SQLException {
		return new RoomMaster(rs.getInt("id"), rs.getString("room_type"), rs.getString("room_num"), rs.getString("bed_num"), rs.getInt("is_avail"));

	}
	@SuppressWarnings("unchecked")
	public List<String> getAvailBeds(String room_type,String room_num) throws SystemException{
		try {
			
			Object[] params = new Object[]{room_type,room_num};
			
			System.out.println("Room TYpe and Room number :::"+room_type+" ::::"+room_num);
			
			List<RoomMaster> lB = (List<RoomMaster>) getListByCriteria("select * from room_master where is_avail=1 and room_type=? and room_num=?", params);
			
		
			List<String> lBedNumbrs=new ArrayList<String>();

			if(lB!=null && lB.size()>0)
			{
			Iterator<RoomMaster> iR = lB.iterator();
			
			while (iR.hasNext()) {
				RoomMaster rm = iR.next();
				if(!lBedNumbrs.contains((String)rm.getBed_num()))
					lBedNumbrs.add(rm.getBed_num());
			}
			System.out.println("Bed Number :::" + lBedNumbrs);
			}
			return lBedNumbrs;
			
		} catch (SystemException e) {
			e.printStackTrace();
			throw e;
		}

	}

	public void insertRoomBooking(RoomMgmt rmgmt, String createdBy) throws SystemException{
		// TODO Auto-generated method stub
		
		try {
			Object[] o = new Object[] { rmgmt.getPat_id(),rmgmt.getDt_admission(),rmgmt.getRes_visit(),rmgmt.getRoom_type(),rmgmt.getRoom_num(),rmgmt.getBed_num(),createdBy,createdBy};
			this.insertRowByQuery("insert into room_mgmt (pat_id,dt_admission,res_visit,room_type,room_num,bed_num,create_n, create_t,update_n,update_t) values(?,?,?,?,?,?,?,now(),?,now())", o);
		} catch (SystemException e) {
			e.printStackTrace();
			throw e;
		}

		
	}

	public void updateRoomMaster(RoomMgmt rmgmt, String updateBy) throws SystemException {
		// TODO Auto-generated method stub
		try {
			Object[] o = new Object[] {updateBy,rmgmt.getRoom_type(),rmgmt.getRoom_num(),rmgmt.getBed_num()};
			this.insertRowByQuery("update room_master set is_avail=0,update_n=?,update_t=now() where room_type=? and room_num=? and bed_num=?", o);
		} catch (SystemException e) {
			e.printStackTrace();
			throw e;
		}

	}
}
