/* EmrAction - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
package com.fort.emr;

import com.fort.sec.action.BaseAction;

public class EMRNewPatient extends BaseAction {

	// not used anywhere...

	public String doExecute() {
		System.out.println("EMRNew Patient please...");
		getSession().put("currentFunc", "EMR");
		return "emrnewpatient-page";
	}

	@Override
	protected String getCurrentFunctionName() {
		return "EMR";
	}
}
