package com.fort.util;

import static com.fort.consts.JSPConstants.COL_DATE_CAL;
import static com.fort.consts.JSPConstants.COL_MULTI_SELECT;
import static com.fort.consts.JSPConstants.COL_PLAIN_TEXT;
import static com.fort.consts.JSPConstants.COL_SINGLE_SELECT;
import static com.fort.consts.JSPConstants.COL_TIME_SELECT;
import static com.fort.consts.JSPConstants.DROPDOWN_ALLERGY_TYPES;
import static com.fort.consts.JSPConstants.DROPDOWN_BILLING_CODES;
import static com.fort.consts.JSPConstants.DROPDOWN_CONDITIONS;
import static com.fort.consts.JSPConstants.DROPDOWN_DUMMY_DATA;
import static com.fort.consts.JSPConstants.DROPDOWN_FAMILY_RELATIONSHIPS;
import static com.fort.consts.JSPConstants.DROPDOWN_FAMILY_RELATIVE_STATUS;
import static com.fort.consts.JSPConstants.DROPDOWN_HPI_TITLES;
import static com.fort.consts.JSPConstants.DROPDOWN_ICD_CODES;
import static com.fort.consts.JSPConstants.DROPDOWN_IMMUNIZATIONS;
import static com.fort.consts.JSPConstants.DROPDOWN_INJECTION_LOCATIONS;
import static com.fort.consts.JSPConstants.DROPDOWN_SOCIAL_HISTORY_NAMES;
import static com.fort.consts.JSPConstants.DROPDOWN_SUBJECTIVE_SYSTEMS;
import static com.fort.consts.JSPConstants.DROPDOWN_SYMPTOMS;
import static com.fort.consts.JSPConstants.DROPDOWN_DOCTORS;
import static com.fort.consts.JSPConstants.DROPDOWN_APPOINTMENT_SLOTS;
import static com.fort.consts.JSPConstants.DROPDOWN_GENDER;
import static com.fort.consts.JSPConstants.TAB_ALLERGIES_TABLE;
import static com.fort.consts.JSPConstants.TAB_ASSESMENT_TABLE;
import static com.fort.consts.JSPConstants.TAB_BILLING_CODE_TABLE;
import static com.fort.consts.JSPConstants.TAB_CHIEF_COMPLAINT_TABLE;
import static com.fort.consts.JSPConstants.TAB_CURRENT_MEICATIONS;
import static com.fort.consts.JSPConstants.TAB_CURRENT_PROBS;
import static com.fort.consts.JSPConstants.TAB_FAMILY_MEDICAL_TABLE;
import static com.fort.consts.JSPConstants.TAB_FOLLOWUP_TABLE;
import static com.fort.consts.JSPConstants.TAB_HPI_TABLE;
import static com.fort.consts.JSPConstants.TAB_IMMUNIZATIONS_TABLE;
import static com.fort.consts.JSPConstants.TAB_MEDICAL_HISTORY_TABLE;
import static com.fort.consts.JSPConstants.TAB_OB_TABLE;
import static com.fort.consts.JSPConstants.TAB_PEDIATRIC_TABLE;
import static com.fort.consts.JSPConstants.TAB_PHYSICAL_EXAM_TABLE;
import static com.fort.consts.JSPConstants.TAB_PREVENTIVE_TABLE;
import static com.fort.consts.JSPConstants.TAB_PSYCHIATRIC_TABLE;
import static com.fort.consts.JSPConstants.TAB_REVIEW_OF_SYSTEM_TABLE;
import static com.fort.consts.JSPConstants.TAB_SOCIAL_HISTORY_TABLE;
import static com.fort.consts.JSPConstants.TAB_SURGIC_TABLE;
import static com.fort.consts.JSPConstants.TAB_TREATMENT_TABLE;
import static com.fort.consts.JSPConstants.TAB_TREATMENT_MEDICATIONS_TABLE;
import static com.fort.consts.JSPConstants.TAB_TREATMENT_LABS_TABLE;
import static com.fort.consts.JSPConstants.TAB_TREATMENT_DIAGNOSTICS_IMAGING_TABLE;
import static com.fort.consts.JSPConstants.TAB_VITALS_TABLE;
import static com.fort.consts.JSPConstants.TAB_DASHBOARD_APPOINTMENTS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.fort.cache.BaseCache;

public class JSPTableConfig {
	private final static HashMap<String, TabCfg> TCfg;
	static {
		//TConfig = new HashMap<String, String[]>();
		TCfg = new HashMap<String, TabCfg>();
		
		// Dashboard Appointments
		TabCfg app = new TabCfg( TAB_DASHBOARD_APPOINTMENTS, "dashboard-appointments", true, true, true, new JSONObject[]{
				new ColCfg( "appointmentTime", "Appointment Timeslot", COL_SINGLE_SELECT, true, BaseCache.getCache(DROPDOWN_APPOINTMENT_SLOTS)).cfg, 
				new ColCfg( "doctorId", "Doctor", COL_SINGLE_SELECT, true, DROPDOWN_DOCTORS ).cfg,
				new ColCfg( "patName", "Patient", COL_PLAIN_TEXT, false ).cfg,
				new ColCfg( "age", "Age", COL_PLAIN_TEXT, false ).cfg,
				new ColCfg( "gender", "Gender", COL_SINGLE_SELECT, false, BaseCache.getCache(DROPDOWN_GENDER) ).cfg,
				new ColCfg( "reasonForVisit", "Reason For Visit", COL_PLAIN_TEXT, true ).cfg,
				new ColCfg( "status", "Status", COL_PLAIN_TEXT, true ).cfg,
				new ColCfg( "billing", "Billing", COL_PLAIN_TEXT, true ).cfg
		});
		TCfg.put(TAB_DASHBOARD_APPOINTMENTS, app);
		
		// History -> Medical History
		TabCfg medHistTabCfg = new TabCfg(TAB_MEDICAL_HISTORY_TABLE, "medical-history", true, new JSONObject[]{
				new ColCfg( "date", "Date", COL_DATE_CAL, true ).cfg,
				new ColCfg( "icdCode", "ICD Code", COL_SINGLE_SELECT, true, DROPDOWN_ICD_CODES ).cfg,
				new ColCfg( "condition", "Condition", COL_SINGLE_SELECT, true, BaseCache.getCache( DROPDOWN_CONDITIONS ) ).cfg,
				new ColCfg( "description", "Description", COL_PLAIN_TEXT, true ).cfg
		} );
		TCfg.put(TAB_MEDICAL_HISTORY_TABLE, medHistTabCfg );
		
		// History -> Surgical History
		TabCfg surgicTabCfg = new TabCfg( TAB_SURGIC_TABLE, "surgic-history", true, new JSONObject[]{
				new ColCfg( "diagnosis", "Diagnosis", COL_PLAIN_TEXT, true ).cfg,
				new ColCfg( "description", "Description", COL_PLAIN_TEXT, true ).cfg
		});
		TCfg.put(TAB_SURGIC_TABLE, surgicTabCfg );
		
		// History -> Psychiatric
		TabCfg psychiatricTab = new TabCfg( TAB_PSYCHIATRIC_TABLE,"psychiatric-history", true, new JSONObject[]{
				new ColCfg( "diagnosis", "Diagnosis", COL_PLAIN_TEXT, true ).cfg,
				new ColCfg( "description", "Description", COL_PLAIN_TEXT, true ).cfg
		});
		TCfg.put(TAB_PSYCHIATRIC_TABLE, psychiatricTab );
		
		//  History -> OB
		TabCfg obTab = new TabCfg(TAB_OB_TABLE, "ob-history", true, new JSONObject[]{
				new ColCfg( "diagnosis", "Diagnosis", COL_PLAIN_TEXT, true ).cfg,
				new ColCfg( "description", "Description", COL_PLAIN_TEXT, true ).cfg
		});
		TCfg.put(TAB_OB_TABLE, obTab );
		
		
		// History -> Pediatric
		TabCfg pediatricTab = new TabCfg(TAB_PEDIATRIC_TABLE, "pediatric-history", true, new JSONObject[]{
				new ColCfg( "diagnosis", "Diagnosis", COL_PLAIN_TEXT, true ).cfg,
				new ColCfg( "description", "Description", COL_PLAIN_TEXT, true ).cfg
		});
		TCfg.put(TAB_PEDIATRIC_TABLE, pediatricTab );
		
		// History -> Allergies/Intolerance
		TabCfg allergiesTab = new TabCfg(TAB_ALLERGIES_TABLE, "allergies-history", true, new JSONObject[]{
				new ColCfg( "type", "Type", COL_SINGLE_SELECT, true, BaseCache.getCache( DROPDOWN_ALLERGY_TYPES ) ).getCfg(),
				new ColCfg( "name", "Name", COL_SINGLE_SELECT, true, BaseCache.getCache( DROPDOWN_DUMMY_DATA )).getCfg(),
				new ColCfg( "symptoms", "Symptoms", COL_MULTI_SELECT, true, BaseCache.getCache( DROPDOWN_SYMPTOMS ) ).getCfg() 
		});
		TCfg.put(TAB_ALLERGIES_TABLE, allergiesTab );
		
		// History -> Family Medical
		TabCfg familyMedicalTab = new TabCfg(TAB_FAMILY_MEDICAL_TABLE, "family-medical-history", true, new JSONObject[]{
				new ColCfg( "relationship", "Relationship",COL_SINGLE_SELECT, true, BaseCache.getCache( DROPDOWN_FAMILY_RELATIONSHIPS ) ).getCfg(),
				new ColCfg( "status", "Status",COL_SINGLE_SELECT, true, BaseCache.getCache( DROPDOWN_FAMILY_RELATIVE_STATUS ) ).getCfg(),
				new ColCfg( "condition", "Condition",COL_SINGLE_SELECT,true, BaseCache.getCache( DROPDOWN_CONDITIONS ) ).getCfg(),
				new ColCfg( "description", "Description",COL_PLAIN_TEXT, true ).getCfg()
		});
		TCfg.put(TAB_FAMILY_MEDICAL_TABLE, familyMedicalTab );
		
		// History -> Social History
		TabCfg socialHistoryTab = new TabCfg(TAB_SOCIAL_HISTORY_TABLE, "social-history", true, new JSONObject[]{
				new ColCfg( "name", "Name", COL_SINGLE_SELECT, true, BaseCache.getCache( DROPDOWN_SOCIAL_HISTORY_NAMES ) ).getCfg(), 
				new ColCfg( "description", "Description", COL_PLAIN_TEXT, true).getCfg() 
		});
		TCfg.put(TAB_SOCIAL_HISTORY_TABLE, socialHistoryTab );
		
		
		// Current - Current Problems
		TabCfg currentProblemsTab = new TabCfg(TAB_CURRENT_PROBS, "current-problems-current", true, new JSONObject[]{
				new ColCfg( "icdCode", "ICD Code", COL_SINGLE_SELECT, true, DROPDOWN_ICD_CODES ).getCfg(), 
				new ColCfg( "diagnosis", "diagnosis",COL_PLAIN_TEXT, true ).getCfg(),
				new ColCfg( "description", "Description",COL_PLAIN_TEXT, true ).getCfg()
		});
		TCfg.put( TAB_CURRENT_PROBS, currentProblemsTab );
		
		// Current - Current Medications
		TabCfg currentMedicationsTab = new TabCfg( TAB_CURRENT_MEICATIONS, "current-medications-current", true, new JSONObject[]{
				new ColCfg( "name", "Name", COL_PLAIN_TEXT, true).getCfg(), 
				new ColCfg( "strength", "Strength",COL_PLAIN_TEXT, true ).getCfg(),
				new ColCfg( "formulation", "Formulation",COL_PLAIN_TEXT, true ).getCfg(),
				new ColCfg( "route", "Route",COL_PLAIN_TEXT, true ).getCfg(),
				new ColCfg( "frequency", "Frequency",COL_PLAIN_TEXT, true ).getCfg(),
				new ColCfg( "startDate", "StartDate",COL_DATE_CAL, true ).getCfg(),
				new ColCfg( "endDate", "EndDate",COL_DATE_CAL, true ).getCfg(),
				new ColCfg( "condition", "Condition Treated",COL_PLAIN_TEXT, true ).getCfg()
		});
		TCfg.put(TAB_CURRENT_MEICATIONS, currentMedicationsTab );
		
		// Assessment 
		TabCfg assessmentTab = new TabCfg(TAB_ASSESMENT_TABLE, "assessment-emr", true, new JSONObject[]{
				new ColCfg( "icdCode", "ICD Code", COL_SINGLE_SELECT, true, DROPDOWN_ICD_CODES ).getCfg(), 
				new ColCfg( "diagnosis", "Diagnosis",COL_PLAIN_TEXT, true ).getCfg(),
				new ColCfg( "description", "Description",COL_PLAIN_TEXT, true ).getCfg()
		});
		TCfg.put(TAB_ASSESMENT_TABLE,  assessmentTab );
		
		// EMR Subjective
		// Subjective - Chief Compliant
		TabCfg chiefCompliantTab = new TabCfg(TAB_CHIEF_COMPLAINT_TABLE, "chief-compliant-subjective", true, new JSONObject[]{
				new ColCfg( "description", "Description",COL_PLAIN_TEXT, true ).getCfg()
		});
		TCfg.put(TAB_CHIEF_COMPLAINT_TABLE, chiefCompliantTab );
		
		// Subjective - HPI
		TabCfg hpiTab = new TabCfg(TAB_HPI_TABLE, "hpi-subjective", true, new JSONObject[]{
				new ColCfg( "title", "Title", COL_SINGLE_SELECT, true, BaseCache.getCache( DROPDOWN_HPI_TITLES ) ).getCfg(), 
				new ColCfg( "symptoms", "Symptoms", COL_MULTI_SELECT, true, BaseCache.getCache( DROPDOWN_SYMPTOMS ) ).getCfg(), 
				new ColCfg( "description", "Description",COL_PLAIN_TEXT, true ).getCfg()
		});
		TCfg.put(TAB_HPI_TABLE, hpiTab );

		// Subjective - Review of System
		TabCfg reviewOfSystemTab = new TabCfg(TAB_REVIEW_OF_SYSTEM_TABLE, "review-of-system-subjective", true, new JSONObject[]{
				new ColCfg( "system", "System",COL_SINGLE_SELECT, true, BaseCache.getCache( DROPDOWN_SUBJECTIVE_SYSTEMS ) ).getCfg(), 
				new ColCfg( "description", "Description",COL_PLAIN_TEXT, true ).getCfg()
		});
		TCfg.put(TAB_REVIEW_OF_SYSTEM_TABLE, reviewOfSystemTab  );
		
		// EMR Plan
		// Plan - Treatment
		TabCfg treatmentTabCfg = new TabCfg(TAB_TREATMENT_TABLE, "treatment-plan", true, new JSONObject[]{
				new ColCfg( "diagnosis", "Diagnosis",COL_PLAIN_TEXT, true ).getCfg(),
				new ColCfg( "description", "Description",COL_PLAIN_TEXT, true ).getCfg()
		});
		TCfg.put(TAB_TREATMENT_TABLE, treatmentTabCfg );
		
		// Plan - Treatment - Medications
		TabCfg medicationTabCfg = new TabCfg( TAB_TREATMENT_MEDICATIONS_TABLE, "treatment-medications-plan", true, new JSONObject[]{
			new ColCfg( "name", "Name", COL_PLAIN_TEXT, true ).getCfg(),
			new ColCfg( "strength", "Strengh", COL_PLAIN_TEXT, true ).getCfg(),
			new ColCfg( "formulation", "Formulation", COL_PLAIN_TEXT, true ).getCfg(),
			new ColCfg( "take", "Take", COL_PLAIN_TEXT, true ).getCfg(),
			new ColCfg( "route", "Route", COL_PLAIN_TEXT, true ).getCfg(),
			new ColCfg( "frequency", "Frequency", COL_PLAIN_TEXT, true ).getCfg(),
			new ColCfg( "duration", "Duration", COL_PLAIN_TEXT, true ).getCfg(),
			new ColCfg( "quantity", "Quantity", COL_PLAIN_TEXT, true ).getCfg(),
			new ColCfg( "refills", "Refills", COL_PLAIN_TEXT, true ).getCfg(),
			new ColCfg( "startDate", "StartDate", COL_DATE_CAL, true ).getCfg(),
			new ColCfg( "endDate", "EndDate", COL_DATE_CAL, true ).getCfg(),
			new ColCfg( "condition", "Condition", COL_MULTI_SELECT, true ).getCfg(),
		} );
		TCfg.put(TAB_TREATMENT_MEDICATIONS_TABLE, medicationTabCfg);
		
		//Plan - Treatment - Labs
		TabCfg treatmentLabsTabCfg = new TabCfg( TAB_TREATMENT_LABS_TABLE, "treatment-labs-plan", true, new JSONObject[]{
				new ColCfg( "labCode", "LabCode", COL_PLAIN_TEXT, true ).getCfg(),
				new ColCfg( "name", "Name", COL_PLAIN_TEXT, true ).getCfg()
		} );
		TCfg.put(TAB_TREATMENT_LABS_TABLE, treatmentLabsTabCfg);
		
		//Plan - Treatment - Diagnostics Imaging
		TabCfg treatmentDiagnosticsImagingTabCfg = new TabCfg( TAB_TREATMENT_DIAGNOSTICS_IMAGING_TABLE, "treatment-diagnostics-imaging-plan", true, new JSONObject[]{
				new ColCfg( "labCode", "LabCode", COL_PLAIN_TEXT, true ).getCfg(),
				new ColCfg( "type", "Type", COL_PLAIN_TEXT, true ).getCfg(),
				new ColCfg( "name", "Name", COL_PLAIN_TEXT, true ).getCfg()				
		} );
		TCfg.put(TAB_TREATMENT_DIAGNOSTICS_IMAGING_TABLE, treatmentDiagnosticsImagingTabCfg);
		
		// Plan - Immunizations
		TabCfg immunizationsTab = new TabCfg(TAB_IMMUNIZATIONS_TABLE, "immunizations-plan", true, new JSONObject[]{
				new ColCfg( "dateGiven", "Date Given", COL_DATE_CAL, true ).getCfg(), 
				new ColCfg( "name", "Name", COL_SINGLE_SELECT, true, BaseCache.getCache( DROPDOWN_IMMUNIZATIONS ) ).getCfg(), 
				new ColCfg( "lotNr", "Lot Nr",COL_PLAIN_TEXT, true ).getCfg(),
				new ColCfg( "expDate", "Exp Date", COL_DATE_CAL, true ).getCfg(), 
				new ColCfg( "dose", "Dose", COL_PLAIN_TEXT, true ).getCfg(), 
				new ColCfg( "route", "Route", COL_PLAIN_TEXT , true).getCfg(), 
				new ColCfg( "location", "Location", COL_SINGLE_SELECT, true, BaseCache.getCache( DROPDOWN_INJECTION_LOCATIONS ) ).getCfg(), 
				new ColCfg( "givenBy", "Given by", COL_PLAIN_TEXT , true).getCfg()
		});
		TCfg.put(TAB_IMMUNIZATIONS_TABLE, immunizationsTab );
		
		// Plan - Preventive
		TabCfg preventiveTab = new TabCfg(TAB_PREVENTIVE_TABLE, "preventive-plan", true, new JSONObject[]{
				new ColCfg( "diagnosis", "Diagnosis",COL_PLAIN_TEXT, true ).getCfg(),
				new ColCfg( "description", "Description",COL_PLAIN_TEXT, true ).getCfg()
		});
		TCfg.put(TAB_PREVENTIVE_TABLE, preventiveTab );
		
		// Plan - Followup
		TabCfg followupTab = new TabCfg(TAB_FOLLOWUP_TABLE, "followup-plan", true, new JSONObject[]{
						new ColCfg( "diagnosis", "Diagnosis",COL_PLAIN_TEXT, true ).getCfg(),
						new ColCfg( "description", "Description",COL_PLAIN_TEXT, true ).getCfg()
				});
		TCfg.put(TAB_FOLLOWUP_TABLE, followupTab );
				
		// EMR Billing
		// Billing Code
		TabCfg billingCodeTab = new TabCfg( TAB_BILLING_CODE_TABLE, "billing-code-billing", true, new JSONObject[]{
				new ColCfg( "icdCode", "ICD Code", COL_SINGLE_SELECT, true, DROPDOWN_ICD_CODES ).getCfg(),
				new ColCfg( "diagnosis", "Diagnosis",COL_PLAIN_TEXT, true ).getCfg(),
				new ColCfg( "description", "Description",COL_PLAIN_TEXT, true ).getCfg()
		});
		TCfg.put(TAB_BILLING_CODE_TABLE, billingCodeTab );
		
		// EMR Objective
		// Objective - Vitals
		TabCfg vitalsTab = new TabCfg( TAB_VITALS_TABLE, "vitals-objective", true, new JSONObject[]{
				new ColCfg( "time", "Time", COL_TIME_SELECT, true ).getCfg(),
				new ColCfg( "heigth", "Height(Ft)", COL_PLAIN_TEXT, true ).getCfg(),
				new ColCfg( "weigth", "Weight(Kg)", COL_PLAIN_TEXT, true ).getCfg(),
				new ColCfg( "bmi", "BMI", COL_PLAIN_TEXT, true ).getCfg(),
				new ColCfg( "temperature", "Temp(F)", COL_PLAIN_TEXT, true ).getCfg(),
				new ColCfg( "hr", "HR", COL_PLAIN_TEXT, true ).getCfg(),
				new ColCfg( "bloodPressure", "BP", COL_PLAIN_TEXT, true ).getCfg(),
				new ColCfg( "rr", "RR", COL_PLAIN_TEXT, true ).getCfg(),
				new ColCfg( "o2", "O2", COL_PLAIN_TEXT, true ).getCfg(),
				new ColCfg( "visionL", "Vision L", COL_PLAIN_TEXT, true ).getCfg(),
				new ColCfg( "visionR", "Vision R", COL_PLAIN_TEXT, true ).getCfg(),
				new ColCfg( "hearingL", "Hearing L", COL_PLAIN_TEXT, true ).getCfg(),
				new ColCfg( "hearingR", "Hearing R", COL_PLAIN_TEXT, true ).getCfg()
		});
		TCfg.put(TAB_VITALS_TABLE, vitalsTab );
		
		// Objective - PHysical Examination
		TabCfg physicalExamTab = new TabCfg( TAB_PHYSICAL_EXAM_TABLE, "physical-exam-objective", true, new JSONObject[]{
				new ColCfg( "title", "Title", COL_PLAIN_TEXT, true ).getCfg(),
				new ColCfg( "symptomsAndDescriptions", "Symptoms and Descriptions", COL_PLAIN_TEXT, true ).getCfg()
		});
		TCfg.put(TAB_PHYSICAL_EXAM_TABLE, physicalExamTab );
	}
	
	public static TabCfg getTableConfig(String tName) {
		return TCfg.get(tName);
	}
	public static String[] getTableColumnNames( String tName ) {
		return TCfg.get(tName).colNames;
	}
	
	public static class TabCfg{
		private final String cfg;
		private String[] colNames;
		TabCfg(String id, String action, boolean editable, JSONObject[] cols){
			this(id, action, editable, false, false, cols);
		}
		TabCfg(String id, String action, boolean editable, boolean searchable, boolean paginate, JSONObject[] cols){ //action without .doo
			JSONObject j = new JSONObject();
			try {
				j.put("id", id);
				j.put("action", action);
				j.put("editable", editable);
				j.put("columns", cols);
				j.put("searchable", searchable);
				j.put("paginate", paginate);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			cfg = j.toString();
			int i = 0; colNames = new String[ cols.length ];
			for(JSONObject jj : cols ){
				try {
					colNames[ i ] = (String)jj.get("dispName");
				} catch (JSONException e) {
					colNames[ i ] = "exceptionOccured";
					e.printStackTrace();
				}
				i++;
			}
		}
		
		public String getCfg(){
			return cfg;
		}
	}
	private static class ColCfg {
		private final JSONObject cfg;
		ColCfg(String id, String dispName, int colType, boolean editable){
			this(id, dispName, colType, editable, null );
		}
		
		private Collection<String> encloseInQuotes(Collection<String> input) {
			Collection<String> output = new ArrayList<String>();
			for(String s: input){
				output.add( String.format( "\"%s\"",s ) );
			}
			return output;
		}
		
		ColCfg(String id, String dispName, int colType, boolean editable, Map<String,String> data){
			cfg = new JSONObject();
			try {
				cfg.put("id", id);
				cfg.put("dispName", dispName);
				cfg.put("colType", colType);
				cfg.put("editable", editable);
				cfg.put("remoteLookAhead", false);
				if(data != null){
					cfg.put("data", encloseInQuotes(data.values()));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		ColCfg(String id, String dispName, int colType, boolean editable, int typeOfData ){ // typeOfData == ICD Codes, billing codes, etc refer to JSPConstants
			cfg = new JSONObject();
			try {
				cfg.put("id", id);
				cfg.put("dispName", dispName);
				cfg.put("colType", colType);
				cfg.put("editable", editable);
				cfg.put("remoteLookAhead", true);
				cfg.put("typeOfData", typeOfData);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		JSONObject getCfg(){
			return cfg;
		}
	}
	
	public static void main(String[] args) {
		Map m = new HashMap<String,String>();
		m.put("key1", "value1");
		m.put("key2", "value2");
		ColCfg g = new ColCfg( "plaintexttest","Plain Test Test", COL_PLAIN_TEXT, true, m);
		System.out.println( new TabCfg("subid", "action", true, new JSONObject[]{ g.getCfg() }).cfg );
	}
}
