package com.fort.roommgmt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.fort.common.exception.SystemException;
import com.fort.sec.action.BaseAction;

public class BedNumberAction extends BaseAction {

	
	@Override
	public String doExecute() throws SystemException {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		
		System.out.println("room_type++++++++++++++++++++"+request.getParameter("roomtype"));
		String room_type=request.getParameter("roomtype");
		String room_num=request.getParameter("roomnum");
		
		RoomManagement rm=new RoomManagement();
		List<String> bed_nums=rm.getAvailBeds(room_type,room_num);
		 String MyDropDownMenu= "";
		  MyDropDownMenu += "<select name=\"bed_num\" id=\"bed_num\" class=\"combobox input-small\">";
		 if(bed_nums.size()>0)
		 {
		 MyDropDownMenu +="<option></option>";
		
		for(String bedNum:bed_nums)
		{
		  MyDropDownMenu += "<option value=\""+bedNum+"\">"+bedNum+"</option>";
		}  
		 }else
		 {
			 MyDropDownMenu +="<option>NO BEDS</option>";
		 }
		  MyDropDownMenu += "</select>";
		
		request.getSession().setAttribute("bed_nums",MyDropDownMenu);
		System.out.println(MyDropDownMenu);
		
		return "success";
	}

	
	
	@Override
	protected String getCurrentFunctionName() {
		return "Room Management";
	}
	
	
}
