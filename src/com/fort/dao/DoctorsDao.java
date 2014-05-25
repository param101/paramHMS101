package com.fort.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fort.common.exception.SystemException;
import com.fort.tos.Doctor;

public class DoctorsDao extends BaseDao {

	private final static DoctorsDao _inst = new DoctorsDao();

	private DoctorsDao() {
	};

	public static DoctorsDao getDaoInst() {
		return _inst;
	}
	
	@SuppressWarnings("unchecked")
	public Map<Integer, String> getActivDocs() {
		try {
			List<Doctor> lDr = (List<Doctor>) getListByCriteria("select * from doctor where active=1", EMPTY_OBJECT_ARRAY);
			HashMap<Integer, String> activeDocs = new HashMap<Integer, String>();

			Iterator<Doctor> iDocs = lDr.iterator();

			while (iDocs.hasNext()) {
				Doctor dr = iDocs.next();
				activeDocs.put(dr.getDoc_id(), "Dr. " + dr.getDoc_first_n() + " " + dr.getDoc_l_nm());

			}
			return activeDocs;
		} catch (SystemException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static void main(String... strings) {
		HashMap<String, String> s = new HashMap<String, String>();

		s.put(new Integer(10).toString(), "ramram");
		s.put(new Integer(15).toString(), "ramram1");
		s.put(new Integer(17).toString(), "ramram2");

		Iterator<String> ir = s.keySet().iterator();

		while (ir.hasNext()) {
			String st = ir.next();
			System.out.println("Key" + st + "Value:" + s.get(st));

		}

	}

	public Object toDataObject(ResultSet rs) throws SQLException {
		return new Doctor(rs.getInt("doc_id"), rs.getString("doc_first_n"), rs.getString("doc_dept"), rs.getTime("doc_dob"), rs.getString("doc_m_nm"), rs.getString("doc_l_nm"), rs.getInt("active"), rs.getString("doc_add"), rs.getString("doc_city"),
				rs.getString("doc_state"), rs.getString("doc_phone"), rs.getString("doc_email"));

	}
}