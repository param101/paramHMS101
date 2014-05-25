package com.fort.sec;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fort.common.exception.SystemException;
import com.fort.consts.CommonConstants;
import com.fort.dao.RoleFuncDao;

public class Func {
	private final int funcId;
	private final String funcName;
	private final String actionUrl;
	
	private final static Map<Integer, Func> FUNC_CACHE = new HashMap<Integer, Func>();
	
	public Func(int funcId, String funcName, String actionUrl) {
		super();
		actionUrl = actionUrl.trim();
		this.funcId = funcId;
		this.funcName = funcName;
		this.actionUrl = actionUrl.endsWith(CommonConstants.URL_SUFFIX)?actionUrl:"/a/"+actionUrl+CommonConstants.URL_SUFFIX;
	}
	public int getFuncId() {
		return funcId;
	}
	public String getFuncName() {
		return funcName;
	}
	public String getActionUrl() {
		return actionUrl;
	}
	
	static {
		/*
		FUNC_CACHE.put( 2, new Func( 2, "EMR", "/j/emrmainpage.doo") );
		FUNC_CACHE.put( 3, new Func( 3, "Schedule", "/j/schedule.doo") );
		FUNC_CACHE.put( 4, new Func( 4, "Resources", "/j/resources.doo") );
		FUNC_CACHE.put( 5, new Func( 5, "Pharmacy", "/j/pharmacy.doo") );
		FUNC_CACHE.put( 6, new Func( 6, "Billing", "/j/billing.doo") );
		//FUNC_CACHE.put( 7, new Func( 7, "HR", "/j/hr.doo") ); 
		*/
		try {
			List<Func> funcs = RoleFuncDao.getDaoInst().getAllActiveFuncs();
			for(Func f: funcs ){
				FUNC_CACHE.put(f.getFuncId(), f);
			}
		} catch (SystemException e) {
			// TODO Handle this properly - MH.fortitude
			e.printStackTrace();
		}
	}
	public static Func getFunc( int fId) throws SystemException {
		// TODO Database call to get function details
		if( FUNC_CACHE.containsKey( fId ) ) {
			return FUNC_CACHE.get( fId );
		} else {
			throw new SystemException("Cannot find the function with function_id: " + fId );
//			Func f = new Func( fId, RandomStringer.getRandomString( 7 ), "/j/home.doo" );
//			synchronized (Func.class) {
//				FUNC_CACHE.put(fId, f );
//			}
//			return f;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Func: [ fId: ").append(funcId).append("; funcName: ").append(funcName)
				.append("; actionUrl: ").append(actionUrl).append(";]");
		return s.toString();
	}
}