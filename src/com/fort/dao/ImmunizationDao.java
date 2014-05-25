package com.fort.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.fort.common.exception.SystemException;
import com.fort.tos.Immunizations;
import com.fort.tos.Medications;

public class ImmunizationDao extends BaseDao {

	private final static ImmunizationDao _inst = new ImmunizationDao();
	private ImmunizationDao(){};
	public static ImmunizationDao getDaoInst(){
		return _inst;
	}
	
	private static final String SELECT_IMMUNIZATION_LIST = "select pat_id,encounter_t,dateGiven,name,lotNr,expDate,dose,route,location,givenBy from emr_plan_immunizations e, emr_encounters c where e.encounter_id=c.encounter_id and pat_id=?";

	
	@Override
	 public Object toDataObject(ResultSet rs) throws SQLException {
		return new Immunizations(rs.getInt("pat_id"),rs.getTime("encounter_t"),rs.getDate("dateGiven"),rs.getString("name"),rs.getString("lotNr"),
				  		rs.getDate("expDate"),rs.getString("dose"),rs.getString("route"),rs.getString("location"),rs.getString("givenBy")); 
	    }
	@SuppressWarnings("unchecked")
	public List<Immunizations> getImmunizationList(int patId) throws SystemException{
		Object[] params = new Object[]{patId};
        List<Immunizations> aList;
        aList = (List<Immunizations>) this.getListByCriteria(SELECT_IMMUNIZATION_LIST, params);
        return aList; 
	}
	
	
}
