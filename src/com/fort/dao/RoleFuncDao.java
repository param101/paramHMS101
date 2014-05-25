package com.fort.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fort.common.exception.BusinessException;
import com.fort.common.exception.SystemException;
import com.fort.sec.Func;
import com.fort.sec.Role;

public class RoleFuncDao extends BaseDao {

	private static final RoleFuncDao _inst = new RoleFuncDao();
	private RoleFuncDao(){}
	private static final int GET_ROLE_IDS = 1;
	private static final int GET_FUNC_IDS_BY_ROLE_IDS = 2;
	private static final int GET_ROLE_NAME_BY_ROLE_ID = 3;
	private static final int GET_ALL_ACTIVE_FUNCS = 4;
	
	public static RoleFuncDao getDaoInst(){
		return _inst;
	}
	private int funcName;
	
	@SuppressWarnings("unchecked")
	private List<Integer> getAllActiveRoleIds() throws SystemException{
		funcName = GET_ROLE_IDS;
		return (List<Integer>) getListByCriteria("select role_id from role where active=1", EMPTY_OBJECT_ARRAY);
	}
	
	@SuppressWarnings("unchecked")
	private List<Integer> getAllFuncIdsByRoleId(int roleId) throws SystemException {
		funcName = GET_FUNC_IDS_BY_ROLE_IDS;
		return((List<Integer> )getListByCriteria("select func_id from role_func_map where role_id=?", new Object[]{ roleId }));
	}
	
	public List<Role> getAllActiveRoles() throws SystemException, BusinessException{
		List<Integer> roleIds = getAllActiveRoleIds();
		Map<Integer, String> inter = new HashMap<Integer, String>(); 
		List<Role> roles = new ArrayList<Role>();
		
		funcName = GET_ROLE_NAME_BY_ROLE_ID;
		for(int r : roleIds)
			inter.put( r, (String)getRowByCriteria("select role_n from role where active = 1 and role_id=?", new Object[]{r}) );
		
		for(int r: roleIds ){
			roles.add( new Role(inter.get(r), r, getAllFuncIdsByRoleId(r)) );
		}
		return roles;
	}
	
	@Override
	public Object toDataObject(ResultSet rs) throws SQLException, SystemException {
		Object ret = null;
		switch( funcName ){
		case GET_ROLE_IDS:
			ret = (Integer)rs.getInt( "role_id" );
			break;
		case GET_FUNC_IDS_BY_ROLE_IDS:
			ret = (Integer)rs.getInt( "func_id" );
			break;
		case GET_ROLE_NAME_BY_ROLE_ID:
			ret = rs.getString( "role_n" );
			break;
		case GET_ALL_ACTIVE_FUNCS:
			ret = new Func(rs.getInt("func_id"), rs.getString( "func_n" ), rs.getString( "url" ));
			break;
		default:
			throw new SystemException( "no funcName defined..");
		}
		return ret; 
	}

	@SuppressWarnings("unchecked")
	public List<Func> getAllActiveFuncs() throws SystemException{
		funcName = GET_ALL_ACTIVE_FUNCS;
		return (List<Func>)getListByCriteria("select func_id, func_n, url from func where active = 1", EMPTY_OBJECT_ARRAY);
	}
	
	public static void main(String[] args) throws SystemException, BusinessException {
		RoleFuncDao test = new RoleFuncDao();
		System.out.println( test.getAllActiveRoleIds() );
		System.out.println( test.getAllFuncIdsByRoleId( 2 ) );
		System.out.println( test.getAllActiveRoles());
		System.out.println( test.getAllActiveFuncs());
	}
}
