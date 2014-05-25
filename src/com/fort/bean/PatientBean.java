package com.fort.bean;

import static com.fort.consts.CommonConstants.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fort.util.RandomStringer;

public class PatientBean {
	private final String fName;
	private final String lName;
	private final Date dob;
	private final Integer patId;
	private final String phone;
	private final String email;
	
	public PatientBean(final String fName, final String lName, final Date dob, final Integer patId, final String phone, final String email) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.dob   = dob;
		this.patId = patId;
		this.phone = phone;
		this.email = email;
		throw new RuntimeException( "did you create this class by mistake????" );
	}
	
	public String toDataTableRow(){
		return toString(DATA_PREFIX, DATA_SEPERATOR, DATA_SUFFIX);
	}
	//don't change the logic of this function.. it will break jsps.
	public String toString( final String prefix, final String sep, final String suffix ){
		StringBuilder s = new StringBuilder();
		return s.append(prefix).append(fName).append(sep).append(lName).append(sep).append(dob).append(sep).append(patId).append(sep).append(phone).append(sep).append(email).append(suffix).toString();
	}

	public String toString() {
		return toDataTableRow();
	}
	public String getfName() {
		return fName;
	}
	public String getlName() {
		return lName;
	}
	public Date getDob() {
		return dob;
	}
	public Integer getPatId() {
		return patId;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	
//	public static List<PatientBean> getDummyData( PatientBean model ) {
//		int max = 100;//new Random(100 ).nextInt();
//		List<PatientBean> r = new ArrayList<PatientBean>();
//		int maxlen=20;
//		for(int i=0;i<max;i++){
////			r.add(new PatientBean(RandomStringer.getRandomStringusingPrefix(model.getfName(), maxlen), 
//					RandomStringer.getRandomStringusingPrefix(model.getlName(), maxlen), RandomStringer.getRandomStringusingPrefix(model.getDob(), maxlen), 
//					RandomStringer.getRandomStringusingPrefix(model.getPatId(), maxlen), RandomStringer.getRandomStringusingPrefix(model.getPhone(), maxlen), 
//					RandomStringer.getRandomStringusingPrefix(model.getEmail(), maxlen)));
//		}
//		return r;
//	}
	
	public static void main(String[] args) {
//		System.out.println(new PatientBean("first", null, null, null, null, null ));
//		System.out.println(getDummyData(new PatientBean("first", null, null, null, null, null )));
	}
}
