package com.fort.roommgmt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.fort.common.exception.SystemException;
import com.fort.sec.action.BaseAction;

public class RoomNumberAction extends BaseAction {

	
	@Override
	public String doExecute() throws SystemException {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		
		System.out.println("room_type++++++++++++++++++++++++++++++++++++++++++++++++++"+request.getParameter("roomtype"));
		String room_type=request.getParameter("roomtype");
		RoomManagement rm=new RoomManagement();
		List<String> room_numbers=rm.getAvailRooms(room_type);
		 String MyDropDownMenu= "";
		  MyDropDownMenu += "<select name=\"room_num\" id=\"room_num\" class=\"combobox input-small\">";
		  if(room_numbers.size()>0)
		  {
		  MyDropDownMenu +="<option></option>";
		
		for(String roomNum:room_numbers)
		{
		  MyDropDownMenu += "<option value=\""+roomNum+"\">"+roomNum+"</option>";
		}  
		 }
		  else
		  {
			  MyDropDownMenu +="<option>NO ROOMS</option>";
		  }
		  MyDropDownMenu += "</select>";
		
		request.getSession().setAttribute("room_nums",MyDropDownMenu);
		
		System.out.println(MyDropDownMenu);
		return "success";
	}

	
	
	@Override
	protected String getCurrentFunctionName() {
		return "Room Management";
	}
	
	
}
