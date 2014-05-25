package com.fort.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.fort.common.exception.SystemException;
import com.fort.tos.Immunizations;
import com.fort.tos.Medications;

public class MedicationsDao extends BaseDao {

	private final static MedicationsDao _inst = new MedicationsDao();
	private MedicationsDao(){};
	public static MedicationsDao getDaoInst(){
		return _inst;
	}

	
	
	
	
	
	
	
	
	private static final String SELECT_MEDICATION_LIST = "select encounter_t,name,strength,formulation,route,frequency,startdate,enddate,conditiontreated from emr_current_medications e, emr_encounters c where e.encounter_id=c.encounter_id and pat_id=?";
	
		
	@SuppressWarnings("unchecked")
	public List<Medications> getMedicationsList( Integer patId ) throws SystemException{
    	Object[] params = new Object[]{patId};
        List<Medications> aList;
        aList = (List<Medications>) this.getListByCriteria(SELECT_MEDICATION_LIST, params);
        
       	return aList;   	
    }

	
	@Override
	 public Object toDataObject(ResultSet rs) throws SQLException {
		return new Medications(rs.getTime("encounter_t"),rs.getString("name"),rs.getString("strength"),rs.getString("formulation"),rs.getString("route"),rs.getString("frequency"),
				  		rs.getDate("startdate"),rs.getDate("enddate"),rs.getString("conditiontreated")); 
	    }
	
}
