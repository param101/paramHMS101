package com.fort.emr.bean;

import com.fort.sec.bean.BaseBean;

public class EmrPlanProcedureBean extends BaseBean {

	private Integer encounterId;
	private String nameOfSurgery;
	private String operatingSurgeon;
	private String assistantSurgeon;
	private String anesthesist;
	private String indicationOfSurgery;
	private String description;
	private String actionType;

	public EmrPlanProcedureBean(Integer encounterId, String nameOfSurgery, String operatingSurgeon,String assistantSurgeon, String anesthesist, String indicationOfSurgery, String description, String actionType) {
		super();
		this.encounterId = encounterId;
		this.nameOfSurgery = nameOfSurgery;
		this.operatingSurgeon = operatingSurgeon;
		this.assistantSurgeon = assistantSurgeon;
		this.anesthesist = anesthesist;
		this.indicationOfSurgery = indicationOfSurgery;
		this.description = description;
		this.actionType = actionType;
	}

	@Override
	public String toString() {
		return String.format("EmrPlanProcedureBean [encounterId=%s, nameOfSurgery=%s, operatingSurgeon=%s, assistantSurgeon=%s, anesthesist=%s, indicationOfSurgery=%s, description=%s, actionType=%s]", 
				encounterId, nameOfSurgery, operatingSurgeon, assistantSurgeon, anesthesist, indicationOfSurgery, description, actionType);
	}

	public Integer getEncounterId() {
		return encounterId;
	}

	public String getNameOfSurgery() {
		return nameOfSurgery;
	}
	public String getOperatingSurgeon() {
		return operatingSurgeon;
	}
	public String getAssistantSurgeon() {
		return assistantSurgeon;
	}
	public String getAnesthesist() {
		return anesthesist;
	}
	public String getIndicationOfSurgery() {
		return indicationOfSurgery;
	}
	public String getDescription() {
		return description;
	}
	public String getActionType() {
		return actionType;
	}
}
