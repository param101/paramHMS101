package com.fort.sec.admin;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class SessionTracker implements HttpSessionBindingListener {
	private final String u;
	private final static Logger log = Logger.getLogger(SessionTracker.class.getName());
	public SessionTracker( String u ) {
		this.u = u;
	}
	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		log.log( Level.INFO,"New Session CREATED for user: " + u + "\nwith event : " + arg0 );
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		log.log( Level.INFO, "Session DESTROYED for user: " + u + "\nwith event : " + arg0 );	}

}
