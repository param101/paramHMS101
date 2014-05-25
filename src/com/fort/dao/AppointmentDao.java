package com.fort.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.fort.common.exception.SystemException;
import com.fort.tos.Appointment;

public class AppointmentDao extends BaseDao {

	private final static AppointmentDao _inst = new AppointmentDao();

	private AppointmentDao() {
	};

	public static AppointmentDao getDaoInst() {
		return _inst;
	}

	public void insertNewAppointment(Appointment ap, String createdBy) throws SystemException {
		try {
			Object[] o = new Object[] { ap.getPat_id(), ap.getDoc_id(), ap.getEncounter_dt(), ap.getApp_slot(), ap.getRes_visit(), createdBy, createdBy };
			this.insertRowByQuery("insert into appointment (pat_id,doc_id,encounter_dt,app_slot,res_visit, created_n, created_t,update_n,update_t) values(?,?,?,?,?,?,now(),?,now())", o);
		} catch (SystemException e) {
			e.printStackTrace();
			throw e;
		}

	}

	public void updateAppointment(Appointment ap, String updatedBy) throws SystemException {
		this.insertRowByQuery("update appointment set doc_id=?, app_slot=?, res_visit=?, update_n=?, update_t=now() where app_id=?", new Object[] { ap.getDoc_id(), ap.getApp_slot(), ap.getRes_visit(), updatedBy, ap.getApp_id() });
	}

	@Override
	public Object toDataObject(ResultSet rs) throws SQLException {
		return new Object();
	}

	public void deleteAppointment(Integer appointmentId) throws SystemException {
		this.insertRowByQuery("delete from appointment where app_id=?", new Object[] { appointmentId });
	}
}