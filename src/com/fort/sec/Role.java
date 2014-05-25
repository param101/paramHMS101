package com.fort.sec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fort.common.exception.BusinessException;
import com.fort.common.exception.SystemException;
import com.fort.dao.RoleFuncDao;

public class Role {
	private final String roleName;
	private final int roleId;
	private final List<Func> functions;
	
	private static final Map<Integer, Role> ROLE_CACHE = new HashMap<Integer, Role>();
	
	public Role(String roleName, int roleId, ArrayList<Func> functions) {
		this.roleName = roleName;
		this.roleId = roleId;
		this.functions = functions;
	}
	
	public Role( String roleName, int roleId, List<Integer> funcIds ) throws SystemException {
		this.roleName=roleName;
		this.roleId=roleId;
		this.functions=new ArrayList<Func>();
		for(int fId:funcIds){
			Func f = Func.getFunc(fId);
			if( f != null ){
				functions.add(f);
			}else{
				//TODO:do this better 
				System.out.println( "ERROR: not able to find function with id: " + fId );
			}
		}
	}
	public String getRoleName() {
		return roleName;
	}
	public int getRoleId() {
		return roleId;
	}
	public List<Func> getFunctions() {
		return functions;
	}
	
	static {
		try {
			List<Role> r = RoleFuncDao.getDaoInst().getAllActiveRoles();
			for(Role rr:r){
				ROLE_CACHE.put(rr.getRoleId(), rr);
			}
		} catch (SystemException  e) {
			// TODO do something better mh.fortitude
			e.printStackTrace();
		} catch( BusinessException e ){
			e.printStackTrace();
		}
		/*
		ArrayList<Func> functions = new ArrayList<Func>();
		for( int i = 1;i< 7; i++ ) {
			functions.add( Func.getFunc( i ));
		}
		
		ROLE_CACHE.put(1, new Role( "Doctor", 1, functions ));
		ROLE_CACHE.put(2, new Role( "Nurse", 2, functions ));
		ROLE_CACHE.put(3, new Role( "Reception", 3, functions ));
		ROLE_CACHE.put(4, new Role( "Pharmacy", 4, functions ));
		ROLE_CACHE.put(5, new Role( "Admin", 5, functions ));
		ROLE_CACHE.put(6, new Role( "Developer", 6, functions ));
		*/
	}
	public static Role getRole( int roleId) throws SystemException {
		if( ROLE_CACHE.containsKey( roleId ) ) {
			return ROLE_CACHE.get( roleId );
		} else {
			throw new SystemException("Cannot find the role with roleId: " + roleId );
//			// TODO Database call to get role details
//			ArrayList<Func> functions = new ArrayList<Func>();
//			for( int i = 1 ; i < 7 ; i++ ) {
//				functions.add( Func.getFunc( i ));
//			}
//			Role r = new Role( RandomStringer.getRandomString(5), roleId, functions );
//			synchronized ( Role.class) {
//				ROLE_CACHE.put(roleId, r );
//			}
//			return r;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Role: [ roleName: ").append(roleName).append("; roleId: ").append(roleId)
				.append("; functions: ").append(functions).append(";]");
		return s.toString();
	}
	
	public static boolean isRoleIdValid(int in){
		return ROLE_CACHE.containsKey(in);
	}
	
	public static void main(String[] args) {
		System.out.println(isRoleIdValid(Integer.parseInt("10")));
	}
	
	private static Map<Integer,String> ROLE_ID_TO_ROLE_NAME_MAP = new HashMap<Integer,String>();
	static{
		Iterator<Integer> it = ROLE_CACHE.keySet().iterator();
		while( it.hasNext() ){
			Integer i = it.next();
			ROLE_ID_TO_ROLE_NAME_MAP.put(i, ROLE_CACHE.get(i).roleName);
		}
		
	}
	public static Map<Integer,String> getRoleIdToRoleNameMap(){
		return ROLE_ID_TO_ROLE_NAME_MAP;
	}
}
