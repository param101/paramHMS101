/* LogoffAction - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
package com.fort.sec.action;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class LogoffAction extends BaseAction {
    public String doExecute() {
	HttpSession session = ServletActionContext.getRequest().getSession();
	session.invalidate();
	return "home";
    }

	@Override
	protected String getCurrentFunctionName() {
		return "";
	}
}
