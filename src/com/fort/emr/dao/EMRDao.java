package com.fort.emr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import com.fort.common.exception.BusinessException;
import com.fort.common.exception.SystemException;
import com.fort.dao.BaseDao;
import com.fort.emr.Encounter;
import com.fort.emr.bean.EMRTableBean;
import com.fort.emr.bean.EMRTableBean.QueryParams;
import com.fort.emr.bean.EMRTableGenericBean;
import com.fort.emr.bean.EmrPlanProcedureBean;
import com.fort.emr.bean.EmrPlanTreatReferralsBean;
import com.fort.emr.bean.IRowAware;

public class EMRDao extends BaseDao {
	
	private final static EMRDao _inst = new EMRDao();
	private EMRDao(){};
	public static EMRDao getDaoInst(){
		return _inst;
	}
	
	private Integer funcId;
	private final Object lock = new Object();
	private EMRTableBean bean;

	private final static String GET_ENCOUNTERS="select encounter_id, pat_id, doc_id, encounter_t, locked from emr_encounters where pat_id=? order by encounter_t desc";
	
	@Override
	public Object toDataObject(ResultSet rs) throws SQLException, SystemException {
		switch( funcId ){
		case 5000:
			return new EMRTableGenericBean(rs.getInt("encounter_id"),rs.getInt("row"), null);
		case 6000:
			if(bean != null)
				return bean.getBeanFromResultSet(rs);
			break;
		case 7000:
			if(rs.getInt("pat_id") != 0 && rs.getTime("encounter_t") != null )
				return true;
			else
				return false;
		case 7123:
			return new EmrPlanProcedureBean(rs.getInt("encounter_id"), rs.getString("name_of_surgery"), rs.getString("operating_surgeon"), rs.getString("assistant_surgeon"), rs.getString("anesthesist"), rs.getString( "indication_of_surgery"), rs.getString( "description" ), null );
		case 7124:
			return new EmrPlanTreatReferralsBean(rs.getInt("encounter_id"), null, rs.getString("speciality"), rs.getString("consultant"), rs.getString("send_via"), rs.getString("reason_for_referral"), rs.getString("internal_comments"), rs.getString("letter"));
		case 7125:
			return new Encounter(rs.getInt("encounter_id"), rs.getInt("pat_id"), rs.getInt("doc_id"), rs.getTimestamp("encounter_t"), rs.getBoolean("locked"));
		default: 
			return new EMRTableGenericBean(rs.getInt("encounter_id"), rs.getInt("row"), null);			
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Encounter> getPatientEncounters(Integer patId) throws SystemException{
		synchronized (lock) {
			funcId=7125;
			return (List<Encounter>) getListByCriteria(GET_ENCOUNTERS, new Object[]{patId});
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<EMRTableBean> getDataByPatIdAndEncounterDate( EMRTableBean bean ) throws SystemException {
		synchronized (lock) {
			funcId=5000;
			System.out.println("querying for encounterId: " + bean.getEncounterId());
			return (List<EMRTableBean>) getListByCriteria("select encounter_id, row from "+bean.getTableName()+" where encounter_id=? ", new Object[]{bean.getEncounterId()});
		}
	}
	public boolean saveRecord( EMRTableBean bean, String updateUser ) throws SystemException{
		List<EMRTableBean> records = getDataByPatIdAndEncounterDate(bean);
		EMRTableBean record = getRecordByRowId( records, bean.getRow() );
		if(record != null ){
			return updateRecord(bean, updateUser );
		} else {
			int row = bean.getRow();
			while(row-1 != 0 && getRecordByRowId(records, row-1) == null ){
				System.out.println("there are gaps; input row: " + row);
				row--;
			}
			System.out.println("inserting at row: "+ row);
			return insertRecord( bean, updateUser);
		}
	}
	
	private <T extends IRowAware> T getRecordByRowId(List<T> beans, Integer row) {
		if( beans != null )
			for(T bean : beans ) {
				if(bean.getRow().equals(row))
					return bean;
			}
		return null;
	}
	private void ensureEncounterDateEntry( Integer patId, Time encounterTime, String updateUser ) throws SystemException {
		synchronized (lock) {
			funcId = 7000;
			try {
				Boolean isGood = (Boolean)super.getRowByCriteria("select encounter_id, pat_id, encounter_t from emr_encounters where pat_id=? and encounter_t=?", new Object[]{patId, encounterTime});
				if( isGood == null || !isGood ) {
					super.insertRowByQuery("insert into emr_encounters (pat_id, encounter_t, create_u, create_t, update_u, update_t) values( ?, ?, ?, now(),null, null )", new Object[]{patId, encounterTime, updateUser});
				}
			} catch (BusinessException e) {
				// TODO: found duplicate rows.. need to handle this later
				e.printStackTrace();
			}	
		}
	}
	private boolean insertRecord( EMRTableBean bean, String updateUser ) throws SystemException{
		///////////////////////////////////////////////////////////////////////////////////////////////////// commented the below line. revist again please
		//ensureEncounterDateEntry(bean.getPatId(), bean.getEncounterDate(), updateUser);
		QueryParams qp = bean.insertQuery(updateUser);
		System.out.println( String.format("insertRecord: Data{%s}, Query{ %s with %s}\n", bean, qp.getQuery(), qp.getParams()));
		int returnValue = super.insertRowByQuery( qp.getQuery(), qp.getParams() );
		if(returnValue == 1)
			return true;
		// TODO: what if returnValue is not 1?
		return false;
	}
	
	private boolean updateRecord( EMRTableBean bean, String updateUser ) throws SystemException {
		QueryParams qp = bean.updateQuery(updateUser);
		System.out.println( String.format("updateRecord: Data{%s}, Query{ %s with %s}\n", bean, qp.getQuery(), qp.getParams()));
		int returnValue = super.insertRowByQuery( qp.getQuery(), qp.getParams() );
		if( returnValue == 1)
			return true;
		// TODO: what if returnValue is not 1?
		return false;
	}
	
	public boolean deleteRecord(EMRTableBean bean, String updateUser) throws SystemException {
		QueryParams qp = bean.deleteQuery(updateUser);
		System.out.println( String.format("deleteRecord: Data{%s}, Query{ %s with %s}\n", bean, qp.getQuery(), qp.getParams()));
		int returnValue = super.insertRowByQuery( qp.getQuery(), qp.getParams() );
		if(returnValue != 1)
			return false;
		// TODO: what if returnValue is not 1?
		List<EMRTableBean> records = getDataByPatIdAndEncounterDate(bean);//getHpiDataByPatIdAndEncounterDate(bean.getPatId(), bean.getEncounterDate());
		int row = bean.getRow();
		while(getRecordByRowId(records, row+1) != null){
			qp = bean.shiftRowUp(row+1, row);
			row++;
			returnValue = super.insertRowByQuery(qp.getQuery(), qp.getParams());
			// TODO: what do to if update fails?
		}
		return false;
	}

	public <T extends EMRTableBean> List<T> refreshData(T criteriaBean) throws SystemException {
		QueryParams qp = criteriaBean.refreshQuery();
		System.out.println( String.format("refreshData: Data{%s}, Query{ %s with %s}\n", criteriaBean, qp.getQuery(), qp.getParams()));
		synchronized (lock) {
			funcId = 6000;
			bean = criteriaBean;
			@SuppressWarnings("unchecked")
			List<T> data = (List<T>) super.getListByCriteria( qp.getQuery(), qp.getParams() );
			bean = null;
			return data;
		}
	}
	public EmrPlanProcedureBean getPlanProcedureData(Integer encounterId ) throws SystemException, BusinessException {
		synchronized (lock) {
			funcId = 7123;
			EmrPlanProcedureBean data = (EmrPlanProcedureBean) getRowByCriteria("select encounter_id, name_of_surgery, operating_surgeon, assistant_surgeon, anesthesist, indication_of_surgery, description from emr_plan_procedures where encounter_id = ?", new Object[]{ encounterId });
			return data;
		}
	}
	
	public int savePlanProcedureData(EmrPlanProcedureBean b, String updateUser ) throws SystemException, BusinessException {
		if(getPlanProcedureData(b.getEncounterId()) == null ) {
			int returnValue = super.insertRowByQuery( "insert into emr_plan_procedures values( ?, ?, ?, ?, ?, ?, ?, ?, now(), null, null )", new Object[]{b.getEncounterId(), b.getNameOfSurgery(), b.getOperatingSurgeon(), b.getAssistantSurgeon(), b.getAnesthesist(), b.getIndicationOfSurgery(), b.getDescription(), updateUser } );
			return returnValue;
		} else {
			int returnValue = super.insertRowByQuery("update emr_plan_procedures set name_of_surgery=?, operating_surgeon=?, assistant_surgeon=?, anesthesist=?, indication_of_surgery=?, description=?, update_u=?, update_t=now() where encounter_id=?", 
								new Object[]{b.getNameOfSurgery(), b.getOperatingSurgeon(), b.getAssistantSurgeon(), b.getAnesthesist(), b.getIndicationOfSurgery(), b.getDescription(), updateUser, b.getEncounterId()});
			return returnValue;
		}
		
	}
	public int savePlanTreatReferralsData( EmrPlanTreatReferralsBean b, String updateUser) throws SystemException, BusinessException {
		if(getPlanTreatReferralsData( b.getEncounterId()) == null ) {
			int returnValue = super.insertRowByQuery( "insert into emr_plan_treat_referrals values( ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), null, null )", 
					new Object[]{ b.getEncounterId(), b.getSpeciality(), b.getConsultant(), b.getSendVia(), b.getReasonForReferral(), null, b.getInternalComments(), b.getLetter(), updateUser } );
			return returnValue;
		} else {
			int returnValue = super.insertRowByQuery("update emr_plan_treat_referrals set speciality=?, consultant=?, send_via=?, reason_for_referral=?, attachment=?, internal_comments=?, letter=?, update_u=?, update_t=now() where encounter_id=?", 
								new Object[]{b.getSpeciality(), b.getConsultant(), b.getSendVia(), b.getReasonForReferral(), null, b.getInternalComments(), b.getLetter(), updateUser, b.getEncounterId()});
			return returnValue;
		}
	}
	public EmrPlanTreatReferralsBean getPlanTreatReferralsData(Integer encounterId) throws SystemException, BusinessException {
		synchronized (lock) {
			funcId = 7124;
			EmrPlanTreatReferralsBean data = (EmrPlanTreatReferralsBean) getRowByCriteria("select encounter_id, speciality, consultant, send_via, reason_for_referral, attachment, internal_comments, letter from emr_plan_treat_referrals where encounter_id = ?", new Object[]{ encounterId });
			return data;
		}
	}
	
	public int createNewEncounterId( Encounter input, String updateUser ) throws BusinessException, SystemException{
		if(input.getPatId() == 0 || input.getDocId() == 0 )
			throw new BusinessException("invalid input to create encounter");
		Timestamp t = getCurrentTime();
		super.insertRowByQuery("insert into emr_encounters (pat_id, encounter_t, doc_id, locked, create_u, create_t, update_u, update_t) values(?, ?, ?, 0, ?, now(), null, null)",
				new Object[]{input.getPatId(), t, input.getDocId(), updateUser });
		
		return getEncounterIdByCriteria(new Encounter(0, input.getPatId(), input.getDocId(), t, false));
	}
	
	protected int getEncounterIdByCriteria(Encounter e) throws SystemException, BusinessException {
		synchronized (lock) {
			funcId=7125;
			e = (Encounter)getRowByCriteria("select encounter_id, pat_id, encounter_t, doc_id, locked from emr_encounters where pat_id=? and encounter_t=? and doc_id=?", new Object[]{e.getPatId(),e.getEncounterTime(),e.getDocId()});
			return e.getEncounterId();
		}
	}
	public Encounter getPatientEncounterByEncounterId(Integer encounterId) throws SystemException, BusinessException {
		synchronized (lock) {
			funcId=7125;
			return (Encounter)getRowByCriteria("select encounter_id, pat_id, encounter_t, doc_id, locked from emr_encounters where encounter_id=?", new Object[]{encounterId});
		}
	}
	
	public int updateLockUnlockEncounter(Integer encounterId, boolean lock, Timestamp encounterT, String updateUser ) throws SystemException{
		return insertRowByQuery("update emr_encounters set locked=?, encounter_t=?, update_u=?, update_t=now() where encounter_id=?", new Object[]{lock, encounterT, updateUser, encounterId});
	}
}
