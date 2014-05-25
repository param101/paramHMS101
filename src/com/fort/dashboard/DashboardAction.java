package com.fort.dashboard;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;

import com.fort.bean.DashBoardOutPatBean;
import com.fort.common.exception.SystemException;
import com.fort.dao.DashboardDao;
import com.fort.sec.action.BaseAction;

public class DashboardAction extends BaseAction
{
	private Date date;
    public String doExecute() {
	log.log(Level.INFO, "dashboardaction please..." + this );
	//try {
		if(date == null )
			date= new Date(Calendar.getInstance().getTime().getTime());
		//List<DashBoardOutPatBean> rs = DashboardDao.getDaoInst().getDashboardOutPatList(date);
		//logInfo( "inside DashboardAction: ret from dao: " + rs );
		//getSession().put("DashboardOutPatList", rs);
		getRequest().put("date", date);
	//} catch (SystemException e) {
//		e.printStackTrace();
//		addErrToReq( e.getMessage() );
//		return "error";
//	}
	return "dashboard-page";
    }

	@Override
	protected String getCurrentFunctionName() {
		return "Dashboard";
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return String.format("DashboardAction [date=%s]", date);
	}
}
