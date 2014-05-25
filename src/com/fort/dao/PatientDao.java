package com.fort.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.fort.common.exception.BusinessException;
import com.fort.common.exception.SystemException;
import com.fort.tos.Patient;

public class PatientDao extends BaseDao {

	private final static PatientDao _inst = new PatientDao();

	private PatientDao() {
	};

	public static PatientDao getDaoInst() {
		return _inst;
	}

	private boolean queryAppender(StringBuilder sb, String colName, String str,
			boolean useAnd, boolean useLike) {
		if (str != null && str.length() != 0) {
			if (useAnd)
				sb.append(" and ");
			sb.append(" ").append(colName).append(" like '%").append(str)
					.append("%' ");
			useAnd = true;
		}
		return useAnd;
	}

	private boolean queryAppender(StringBuilder sb, String colName, Date date,
			boolean useAnd) {
		if (date != null) {
			if (useAnd)
				sb.append(" and ");
			sb.append(" ").append(colName).append("='").append(date)
					.append("' ");
			useAnd = true;
		}
		return useAnd;
	}

	public static void main(String... connection) {

		try {
			Patient p = (Patient) new PatientDao()
					.getRowByCriteria(
							"select * from patient where pat_id=(select max(pat_id) from patient)",
							null);
			System.out.println("ID ::::::::::" + p.getPatId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public int insertNewPatient(Patient p, String createdBy) throws Exception {
		try {

			System.out.println("date :::::::::::::::::::::::::::::"
					+ p.getDob());
			Object[] o = new Object[] { p.getTitle(), p.getFname(),
					p.getMname(), p.getLname(), p.getDob(), p.getGender(),
					p.getAddress(), p.getCity(), p.getState(), p.getZip_cd(),
					p.getPhone(), p.getEmail(), createdBy, createdBy };
			this.insertRowByQuery(
					"insert into patient (pat_prefix,pat_first_n, pat_mid_init,pat_last_n , pat_dob, pat_gender , pat_add, pat_city, pat_state,pat_zip,pat_pri_phone,pat_email,created_n,created_t,update_n,update_t)"
							+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?,now())",
					o);
			Patient newPatient = (Patient) this
					.getRowByCriteria(
							"select * from patient where pat_id=(select max(pat_id) from patient)",
							null);

			return newPatient.getPatId();

		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;

		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;

		}

	}

	public List<Patient> findPatientsByCriteria(Patient searchCriteria)	throws SystemException {
		StringBuilder sb = new StringBuilder(
				"select pat_id, pat_prefix, pat_first_n, pat_mid_init, pat_last_n, pat_dob, pat_gender, pat_add, pat_city,pat_state,pat_zip,pat_pri_phone, pat_email,"
						+ "pat_ins_carrier, pat_ins_grNum, pat_ins_memNum, pat_ins_sub, pat_ins_rel, pat_emrginfo_name, pat_emrginfo_rel, pat_emrginfo_ph, pat_emrginfo_email, pat_maritalStat, pat_religion, pat_diet, pat_profession, pat_income"
						+ ", created_n, created_t from patient where ");
		boolean useAnd = false;
		if (searchCriteria.getPatId() != null && searchCriteria.getPatId() != 0) {
			sb.append("pat_id=").append(searchCriteria.getPatId());
			useAnd = true;
		}
		useAnd = queryAppender(sb, "pat_first_n", searchCriteria.getFname(), useAnd, true);
		useAnd = queryAppender(sb, "pat_last_n", searchCriteria.getLname(), useAnd, true);
		useAnd = queryAppender(sb, "pat_dob", searchCriteria.getDob(), useAnd);
		useAnd = queryAppender(sb, "pat_email", searchCriteria.getEmail(), useAnd, true);
		useAnd = queryAppender(sb, "pat_pri_phone", searchCriteria.getPhone(), useAnd, true);

		System.out.println("using query: " + sb.toString());

		@SuppressWarnings("unchecked")
		List<Patient> list = (List<Patient>) super.getListByCriteria(
				sb.toString(), new Object[] {});
		return list;
	}

	public Patient findPatientByPatId(Integer patId) throws SystemException,
			BusinessException {
		StringBuilder sb = new StringBuilder(
				"select pat_id, pat_prefix, pat_first_n, pat_mid_init, pat_last_n, pat_dob, pat_gender, pat_add, pat_city,pat_state,pat_zip,pat_pri_phone, pat_email,"
						+ "pat_ins_carrier, pat_ins_grNum, pat_ins_memNum, pat_ins_sub, pat_ins_rel, pat_emrginfo_name, pat_emrginfo_rel, pat_emrginfo_ph, pat_emrginfo_email, pat_maritalStat, pat_religion, pat_diet, pat_profession, pat_income"
						+ ", created_n, created_t from patient where pat_id=?");
		Patient pat = (Patient) this.getRowByCriteria(sb.toString(),
				new Object[] { patId });
		System.out
				.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"
						+ pat);
		return pat;
	}

	@Override
	public Object toDataObject(ResultSet rs) throws SQLException,
			SystemException {
		return new Patient(rs.getString("pat_prefix"),
				rs.getString("pat_first_n"), rs.getString("pat_mid_init"),
				rs.getString("pat_last_n"), rs.getDate("pat_dob"),
				rs.getString("pat_gender"), rs.getString("pat_add"),
				rs.getString("pat_city"), rs.getString("pat_state"),
				rs.getString("pat_zip"), rs.getString("pat_pri_phone"),
				rs.getString("pat_email"), null,
				rs.getString("pat_ins_carrier"), rs.getString("pat_ins_grNum"),
				rs.getString("pat_ins_memNum"), rs.getString("pat_ins_sub"),
				rs.getString("pat_ins_rel"), rs.getString("pat_emrginfo_name"),
				rs.getString("pat_emrginfo_rel"),
				rs.getString("pat_emrginfo_ph"),
				rs.getString("pat_emrginfo_email"),
				rs.getString("pat_maritalStat"), rs.getString("pat_religion"),
				rs.getString("pat_diet"), rs.getString("pat_profession"),
				rs.getString("pat_income"), rs.getInt("pat_id"));
	}

	public void updatePatientInfo(Patient p,String updatedBy) throws SystemException,BusinessException{
		// TODO Auto-generated method stub
 		
	 		
	 		 Object[] o = new Object[] { p.getAddress(),p.getCity(),p.getState(),p.getZip_cd(),p.getPhone(),p.getEmail(),p.getIns_carrier(),p.getIns_groupNumber(),
	 				 
	             p.getIns_memberNumber(),p.getIns_subscriber(),p.getIns_relationship(),p.getEmrginfo_name(),p.getEmrginfo_relation(),p.getEmrginfo_phone(),p.getEmrginfo_email(),
	             p.getMaritalStat(),p.getReligion(),p.getDiet(),p.getProfession(),p.getIncome(),updatedBy};
	 			this.insertRowByQuery(
	 					"update patient set pat_add=?,pat_city=?, pat_state=?,pat_zip =?, pat_pri_phone=?, pat_email =?, pat_ins_carrier=?, pat_ins_grNum=?," +
	 					" pat_ins_memNum=?,pat_ins_sub=?,pat_ins_rel=?,pat_emrginfo_name=?,pat_emrginfo_rel=?,pat_emrginfo_ph=?," +
	 					"pat_emrginfo_email=?,pat_maritalStat=?,pat_religion=?,pat_diet=?,pat_profession=?,pat_income=?,update_n=?,update_t=now()", o);
	 			
	 		
	 		
	 
	}
}
