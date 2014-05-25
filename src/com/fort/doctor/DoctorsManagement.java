package com.fort.doctor;

import java.util.Map;

import com.fort.dao.DoctorsDao;

public class DoctorsManagement {

	public Map<Integer, String> getActiveDocs() {
		return DoctorsDao.getDaoInst().getActivDocs();
	}
}
