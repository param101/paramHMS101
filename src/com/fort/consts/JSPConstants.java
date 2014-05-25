package com.fort.consts;

public interface JSPConstants {
	String EMR_TABLE_PLACE_HOLDER = "<p></p>";
	String EMR_TABLE_CLOSE_BTN = "<button type=\"button\" id=\"del_row\" class=\"close\" aria-hidden=\"true\">x</button>";
	String EMR_TABLE_ADD_BTN = "<button type=\"button\" id=\"add_row\" class=\"close\" aria-hidden=\"true\">Add a new row</button>";
	String EMR_TABLE_SUB = "emr-subjective";
	
	String EMR_TABLES_LIST = "emr-tables";
	
	//dropdown codes
	int DROPDOWN_SYMPTOMS = 100;
	int DROPDOWN_ICD_CODES = 101;
	int DROPDOWN_CONDITIONS = 103;
	int DROPDOWN_ALLERGY_TYPES = 104;
	int DROPDOWN_DUMMY_DATA = 105;
	int DROPDOWN_FAMILY_RELATIONSHIPS = 106;
	int DROPDOWN_FAMILY_RELATIVE_STATUS = 107;
	int DROPDOWN_SOCIAL_HISTORY_NAMES = 108;
	int DROPDOWN_HPI_TITLES = 109;
	int DROPDOWN_SUBJECTIVE_SYSTEMS = 110;
	int DROPDOWN_IMMUNIZATIONS = 111;
	int DROPDOWN_INJECTION_LOCATIONS = 112;
	int DROPDOWN_BILLING_CODES = 113;
	int DROPDOWN_APPOINTMENT_SLOTS = 114;
	int DROPDOWN_DOCTORS = 115;
	int DROPDOWN_GENDER = 116;
	
	int COL_PLAIN_TEXT = 200;
	int COL_SINGLE_SELECT = 201;
	int COL_DATE_CAL = 202;
	int COL_MULTI_SELECT = 203;
	int COL_TIME_SELECT = 204;
	
	// dashboard - appointments
	String TAB_DASHBOARD_APPOINTMENTS = "DashboardAppointmentsTable";
	
	//emr-history
	String TAB_MEDICAL_HISTORY_TABLE = "MedicalHistoryTable";
	String TAB_SURGIC_TABLE = "SurgicTable";
	String TAB_PSYCHIATRIC_TABLE = "PsychiatricTable";
	String TAB_OB_TABLE = "OBTable";
	String TAB_PEDIATRIC_TABLE = "PediatricTable";
	String TAB_ALLERGIES_TABLE = "AllergiesTable";
	String TAB_FAMILY_MEDICAL_TABLE = "FamilyMedicalTable";
	String TAB_SOCIAL_HISTORY_TABLE = "SocialHistoryTable";
	
	//emr-current
	String TAB_CURRENT_PROBS = "CurrentProblemsTable";
	String TAB_CURRENT_MEICATIONS = "CurrentMedicationsTable";
	
	//emr-assesment
	String TAB_ASSESMENT_TABLE = "AssesmentTable";
	
	//emr-subjective
	String TAB_CHIEF_COMPLAINT_TABLE = "ChiefComplaintTable";
	String TAB_HPI_TABLE = "HPITable";
	String TAB_REVIEW_OF_SYSTEM_TABLE = "ReviewOfSystemTable";
	
	//emr-plan
	String TAB_TREATMENT_TABLE = "TreatmentTable";
	String TAB_IMMUNIZATIONS_TABLE = "ImmunizationsTable";
	String TAB_PREVENTIVE_TABLE = "PreventiveTable";
	String TAB_FOLLOWUP_TABLE = "FollowupTable";
	
	//emr-plan-treatment-medications
	String TAB_TREATMENT_MEDICATIONS_TABLE = "TreatmentMedicationsTable";
	String TAB_TREATMENT_LABS_TABLE = "TreatmentLabsTable";
	String TAB_TREATMENT_DIAGNOSTICS_IMAGING_TABLE = "TreatmentDiagnosticsImagingTable";
	
	
	//emr-billing
	String TAB_BILLING_CODE_TABLE = "BillingCodeTable";
	
	//emr-objective
	String TAB_VITALS_TABLE = "VitalsTable";
	String TAB_PHYSICAL_EXAM_TABLE = "PhysicalExamTable";
	
	String COL_CONFIG_SELECT = "~select";
	String COL_CONFIG_CAL = "~cal";
	String COL_CONFIG_MULTISELECT = "~multiselect";
	String COL_CONFIG_TIME = "~timepicker";
	
	// navigation constants
	int NAV_FROM_DASHBOARD = 1;
	int NAV_FROM_EMR_SEARCH = 2;
}
