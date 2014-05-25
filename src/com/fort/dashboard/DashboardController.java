package com.fort.dashboard;

import static com.fort.consts.CommonConstants.ACTION_TYPE_DELETE;
import static com.fort.consts.CommonConstants.ACTION_TYPE_REFRESH;
import static com.fort.consts.CommonConstants.ACTION_TYPE_SAVE;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.samples.mvc.data.custom.RequestAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fort.bean.DashBoardOutPatBean;
import com.fort.common.exception.SystemException;
import com.fort.dao.DashboardDao;
import com.fort.sec.action.BaseAction;
import com.fort.util.JSONObjectFort;

@Controller
@SessionAttributes
public class DashboardController extends BaseAction {

	@RequestMapping(value="/a/dashboard",  method = RequestMethod.GET)
	public ModelAndView dashboard(@RequestAttribute("actionType") String actionType) throws SystemException {
		if( actionType == null ) {
			System.out.println( "action type is null. setting it to refresh explicitly");
			actionType = ACTION_TYPE_REFRESH;
		}
		Date date = null;
		logInfo(this);
		if(actionType.equalsIgnoreCase(ACTION_TYPE_REFRESH)){
			if(date == null )
				date= new Date(Calendar.getInstance().getTime().getTime());
			List<DashBoardOutPatBean> data = DashboardDao.getDaoInst().getDashboardOutPatList(date);
			logInfo( "inside DashboardAction: ret from dao: " + data );
			ModelAndView mv = new ModelAndView();
			mv.addObject("DashboardOutPatList", data);
			//getSession().put("DashboardOutPatList", data);
			mv.addObject("date", date);
			//getRequest().put("date", date);
			mv.setViewName("appointments");
			
			JSONObject jsonObj = new JSONObjectFort();
			JSONArray jsonArray = new JSONArray();
			try {
				if(data != null) {
					int i = 1;
					for(DashBoardOutPatBean bean : data){
						JSONObject json = new JSONObjectFort();
						json.put("row", bean.getAppointmentId());
						json.put("appointmentTime", bean.getApp_slots());
						json.put("doctorId", bean.getDocName());
						json.put("patName", bean.getPatName());
						json.put("age",bean.getAge());
						json.put("gender", bean.getGender());
						json.put("reasonForVisit", bean.getReasonForVisit());
						json.put("status", bean.getStatus());
						json.put("billing", "NA");
						json.put("edit", "");//keep this guy empty. required in jsp
						json.put("patId", bean.getPatId());
						jsonArray.put(json);
					}
				}
				jsonObj.put("aaData", jsonArray);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new SystemException(e.getMessage());
			}
			//getRequest().put("jsonData", jsonObj);
			mv.addObject("jsonData", jsonObj);
			logInfo("jsonArray: "+jsonObj.toString());
			//return "json-data";
			return mv;
		} else if(actionType.equalsIgnoreCase(ACTION_TYPE_SAVE)){
			logInfo("save called......................" + this);
			//AppointmentDao.getDaoInst().updateAppointment(new Appointment(0, doctorId, date, appointmentTime, reasonForVisit, status, billing, patName, age, gender, row), getUser().getuId());
		} else if(actionType.equalsIgnoreCase(ACTION_TYPE_DELETE)){
			logInfo("delete called....."+this);
			//AppointmentDao.getDaoInst().deleteAppointment(row);
		}
		//return "dashboard";
		return null;
	}

	@Override
	public String doExecute() throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getCurrentFunctionName() {
		// TODO Auto-generated method stub
		return null;
	}
}