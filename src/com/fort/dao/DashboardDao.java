package com.fort.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.fort.bean.DashBoardOutPatBean;
import com.fort.common.exception.SystemException;

public class DashboardDao extends BaseDao{
	
	private final static DashboardDao _inst = new DashboardDao();
	private DashboardDao(){};
	public static DashboardDao getDaoInst(){
		return _inst;
	}

	private static final String SELECT_OUT_PAT_DASHBOARD = "select app_id, app_slot, concat( 'Dr. ',doc_first_n, ' ', doc_l_nm) as doc_name, concat( pat_first_n,' ',pat_last_n) as pat_name, (to_days(now()) - to_days( p.pat_dob )) / 365 as age, pat_gender, res_visit, p.pat_id from appointment a, patient p, doctor d where a.pat_id=p.pat_id and a.doc_id=d.doc_id and encounter_dt=?";
	
	@SuppressWarnings("unchecked")
	public List<DashBoardOutPatBean> getDashboardOutPatList( Date d ) throws SystemException{
    	Object[] params = new Object[]{d};
        List<DashBoardOutPatBean> aList;
        aList = (List<DashBoardOutPatBean>) this.getListByCriteria(SELECT_OUT_PAT_DASHBOARD, params);
       	return aList;   	
    }
	
    public Object toDataObject(ResultSet rs) throws SQLException {
        return new DashBoardOutPatBean(rs.getString("app_slot"),rs.getString("doc_name"),rs.getString("pat_name"),
        		rs.getShort("age"),rs.getString("pat_gender"),rs.getString("res_visit"),"tobeadded","tobediscussed",false, rs.getInt("app_id"), rs.getInt("pat_id")); 
    }
}