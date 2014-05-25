package com.fort.emr;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Encounter {
	final private Integer encounterId;
	final private Integer patId;
	final private Integer docId;
	final private Timestamp encounterTime;
	final private boolean locked;
	final private String dispName;
	
	public Encounter( final Integer encounterId, final Integer patId, final Integer docId, final Timestamp encounterTime, final boolean locked ){
		this.encounterId=encounterId;
		this.patId=patId;
		this.docId=docId;
		this.encounterTime=encounterTime;
		this.locked=locked;
		dispName=generateDispName();
	}

	public Integer getEncounterId() {
		return encounterId;
	}

	public Timestamp getEncounterTime() {
		return encounterTime;
	}

	public boolean isLocked() {
		return locked;
	}

	public Integer getPatId() {
		return patId;
	}

	public Integer getDocId() {
		return docId;
	}
	private final static String EMR_ENCOUNTER_ID_FORMAT = "yyyy-MM-dd hh:mm a";
	protected String generateDispName(){
		if(encounterTime != null)
			return String.format( "%s - %s", new SimpleDateFormat(EMR_ENCOUNTER_ID_FORMAT).format(encounterTime),docId );
		return null;
	}
	
	public String getDispName(){
		return dispName;
	}
	
	@Override
	public String toString() {
		return String.format("Encounter [encounterId=%s, patId=%s, docId=%s, encounterTime=%s, locked=%s]", encounterId, patId, docId, encounterTime, locked);
	}

}