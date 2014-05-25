package com.fort.emr.bean;

import com.fort.sec.bean.BaseBean;

public class EmrPlanTreatReferralsBean extends BaseBean {
	private Integer encounterId;
	private String actionType;
	private String speciality;
	private String consultant;
	private String sendVia;
	private String reasonForReferral;
	private String internalComments;
	private String letter;
	public EmrPlanTreatReferralsBean(Integer encounterId, String actionType, String speciality, String consultant, String sendVia, String reasonForReferral, String internalComments, String letter) {
		super();
		this.encounterId = encounterId;
		this.actionType = actionType;
		this.speciality = speciality;
		this.consultant = consultant;
		this.sendVia = sendVia;
		this.reasonForReferral = reasonForReferral;
		this.internalComments = internalComments;
		this.letter = letter;
	}
	@Override
	public String toString() {
		return String.format("EmrPlanTreatReferralsBean [encounterId=%s, actionType=%s, speciality=%s, consultant=%s, sendVia=%s, reasonForReferral=%s, internalComments=%s, letter=%s]", 
				encounterId, actionType, speciality, consultant, sendVia, reasonForReferral, internalComments, letter);
	}

	public Integer getEncounterId() {
		return encounterId;
	}
	public String getActionType() {
		return actionType;
	}
	public String getSpeciality() {
		return speciality;
	}
	public String getConsultant() {
		return consultant;
	}
	public String getSendVia() {
		return sendVia;
	}
	public String getReasonForReferral() {
		return reasonForReferral;
	}
	public String getInternalComments() {
		return internalComments;
	}
	public String getLetter() {
		return letter;
	}
}
