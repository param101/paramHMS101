package com.fort.common;

import com.fort.consts.CommonConstants;
import com.fort.sec.Role;

public class Validations {
	
	public static boolean nullOrEmptyCheck(String i){
		return !( i==null || i.length()<=0);
	}
	
	public static boolean nullOrEmptyCheck( String i, int minlen, int maxlen ){
		return (nullOrEmptyCheck(i) && i.length() >= minlen && i.length() <= maxlen );
	}
	
	public static boolean validateUserId( String uid ) {
		return nullOrEmptyCheck(uid, CommonConstants.USER_ID_LEN_MIN, CommonConstants.USER_ID_LEN_MAX);
	}
	
	public static boolean validatePwd( String pwd ) {
		return nullOrEmptyCheck(pwd, CommonConstants.PWD_LEN_MIN, CommonConstants.PWD_LEN_MAX);
	}
	
	public static boolean validateEmail( String email ){
		return nullOrEmptyCheck(email, CommonConstants.EMAIL_LEN_MIN, CommonConstants.EMAIL_LEN_MAX);
	}
	
	public static boolean validateRole( int i){
		return Role.isRoleIdValid(i);
	}
}
