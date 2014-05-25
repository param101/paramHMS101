package com.fort.billing;
import com.fort.sec.action.BaseAction;

public class BillingAction extends BaseAction
{
    public String doExecute() {
    	System.out.println("billingaction please...");
    	getSession().put("currentFunc", "Billing");
    	return "billing-page";
    }

	@Override
	protected String getCurrentFunctionName() {
		return "Billing";
	}
}