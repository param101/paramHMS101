package com.fort.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.fort.common.exception.SystemException;
import com.fort.tos.Encounter;

public class EncounterDao extends BaseDao {

	private final static EncounterDao _inst = new EncounterDao();
	private EncounterDao(){};
	public static EncounterDao getDaoInst(){
		return _inst;
	}


	
	private static final String SELECT_ENCOUNTER = "select app.pat_id,app.doc_id,encounter_dt,app_slot,concat(doc_first_n,' ',doc_l_nm) " +
			"as doc_name,concat(pat_first_n,' ',pat_last_n) as pat_name,20 as age,pat_gender,res_visit from appointment " +
			"app,patient pat, doctor doc where app.doc_id=doc.doc_id and app.pat_id=pat.pat_id and app.pat_id=?";
	@SuppressWarnings("unchecked")
	public List<Encounter> findEncounterList( Integer patId ) throws SystemException{
    	Object[] params = new Object[]{patId};
        List<Encounter> aList;
        aList = (List<Encounter>) this.getListByCriteria(SELECT_ENCOUNTER, params);
        
       	return aList;   	
    }

	
	@Override
	 public Object toDataObject(ResultSet rs) throws SQLException {
		return new Encounter(rs.getInt("pat_id"),rs.getInt("doc_id"),rs.getDate("encounter_dt"),rs.getString("doc_name"),rs.getString("app_slot"),
	        		rs.getString("pat_name"),rs.getString("age"),rs.getString("pat_gender"),rs.getString("res_visit"),"tobediscussed","tobedeiscussed"); 
	    }
	
	
}
