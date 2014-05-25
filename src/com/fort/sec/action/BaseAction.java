package com.fort.sec.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.fort.common.exception.SystemException;
import com.fort.sec.User;
import com.fort.util.StringUtils;

public abstract class BaseAction /*implements SessionAware, RequestAware */{
	private Map<String, Object> session;
	private Map<String, Object> request;
	private List<String> errList;
	private List<String> msgList;
	protected HttpServletResponse response;
	protected User user;
	
	private final String loginPath = "/j/login.doo";
	protected final static Logger log = Logger.getLogger(BaseAction.class.getName());

	protected final void logInfo(Object o) {
		log(o, Level.INFO);
	}
	private static final String NO_USER="[NO-USER]: ";
	protected final void log( Object o, Level l){
		String u = (user==null?NO_USER:StringUtils.enclose(user.getuId(), StringUtils.SQUARE_BRACKETS))+": ";
		log.log(l, u+(o==null?null:o.toString()));
	}
	protected final void logAll(Object o) {
		log(o.toString(), Level.ALL);
	}
	protected final void logFine(Object o) {
		log(o.toString(), Level.FINE);
	}
	
	
	public final void setRequest(Map r) {
		request = r;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public final void setSession(Map s) {
		session = s;
	}

	protected User getUser(){
		return user;
	}
	
	public final String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		logInfo("request.getRequestURI: "+request.getRequestURI());
		request.setAttribute("ActionAck", true); //to ensure that all requests are backed by Action class. No JSP Should be invoked directly
		this.user = (User)session.getAttribute("user");
		
		if (request.getRequestURI().toLowerCase().contains(loginPath.toLowerCase())) {
			if (user != null)
				logInfo("user alredy logged in...."+user);
			else
				logInfo("new loginrequest");
		} else if (user != null)
			logInfo("user is logged in...."+user);
		else {
			addErrToReq("Sorry, your session is expired, please login");
			logInfo( "user is not logged in. redirect to login page.");
			return "login-page";
		}
		String nextPage = null;
		try{
			nextPage = doExecute();
		}catch( SystemException s ){
			s.printStackTrace();
			nextPage="error";
		}
		addErrMsgToResponse();
		putFuncNameToSessionNoFail();
		logInfo( "Forwarding to '"+nextPage+"' with msg: [|"+request.getAttribute("msg")+"|]");
		logInfo( "Forwarding to '"+nextPage+"' with err: [|"+request.getAttribute("err")+"|]");
		
		return nextPage;
	}
	
	private void putFuncNameToSessionNoFail(){
		try{
			getSession().put("currentFunc", getCurrentFunctionName());	
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setHeader("currentFunc", getCurrentFunctionName());
		} catch( Throwable t) {
			t.printStackTrace();
		}
	}
	private void addErrMsgToResponse() {
		if( errList != null && errList.size() > 0 ){
			this.request.put("err", errList );
		}
		if( msgList != null && msgList.size() > 0 ){
			logInfo( "test: adding msg" + msgList );
			this.request.put("msg", msgList);
		}
	}
	
	protected void addErrToReq( String err ) {
		if(errList == null)
			errList = new ArrayList<String>();
		errList.add(err);
	}
	
	protected void addMsgToReq( String msg ) {
		if(msgList == null)
			msgList = new ArrayList<String>();
		msgList.add(msg);
		logInfo("adding "  + msgList + " to request" );
	}

	protected String pageNameFromUrl( String s ){
		Pattern p = Pattern.compile("/a/(.*).doo");
		Matcher m  = p.matcher(s);
		if( m.find()){
			return m.group(1);
		}
		return s;
	}
	
	public abstract String doExecute() throws SystemException;
	protected abstract String getCurrentFunctionName();
	
	public static void main(String[] args) {
		System.out.println(new BaseAction(){
			@Override
			public String doExecute() throws SystemException {
				return null;
			}
			@Override
			protected String getCurrentFunctionName() {
				return null;
			}
		}.pageNameFromUrl("/j/dashboard.doo") );
	}
}
