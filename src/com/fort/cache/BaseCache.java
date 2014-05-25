package com.fort.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fort.common.exception.SystemException;
import com.fort.dao.AppSlotsDao;
import com.fort.dao.CachingDao;
import com.fort.dao.DoctorsDao;
import com.fort.tos.AppSlots;
import com.fort.util.RandomStringer;

import static com.fort.consts.JSPConstants.*;

public abstract class BaseCache {
	
	private final static Map<Integer,Map<String,String>> cache = new HashMap<Integer,Map<String,String>>();
	
	//private final static Map<String,String> cache_icd_codes;
	
	public static Map<String,String> getCache(final int id){
		if(!cache.containsKey(id))
			throw new RuntimeException( "could not find cache entry for : " + id );
		return cache.get(id);
	}
	
	protected final static void addToCache( final int id, final Map<String,String> toAdd){
		cache.put(id, toAdd);
	}
	
	static{
		Map<String, String> symptoms = new HashMap<String, String>();
		symptoms.put("1", "Head Ache");
		symptoms.put("2", "Stomach Ache");
		symptoms.put("3", "Acidity");
		symptoms.put("4", "Eye Burning");
		addToCache(DROPDOWN_SYMPTOMS, symptoms);
		System.out.println( "Adding Symptoms to cache..");
	}
	
	static{
		//HashMap<Integer, String> icdCodes = new HashMap<Integer, String>();
		//populateRandomDataByPrefix( icdCodes, 10, "ICD Code ");
		
		Map<String, String> icdCodes = new HashMap<String, String>();
		try {
			icdCodes = CachingDao.getDaoInst().getICDCodes();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		addToCache(DROPDOWN_ICD_CODES, icdCodes);
		System.out.println( "Adding ICD Codes to cache..");
	}
	
	static{
		List<AppSlots> appSlots = new ArrayList<AppSlots>();
		try {
			appSlots = AppSlotsDao.getDaoInst().getAppSlots();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		Map<String, String> appSlotsMap = new HashMap<String, String>();
		for(AppSlots slot : appSlots ){
			appSlotsMap.put(slot.getId().toString(), slot.getApp_slots());
		}
		addToCache(DROPDOWN_APPOINTMENT_SLOTS, appSlotsMap );
	}
	static{
		Map<Integer, String> docs = DoctorsDao.getDaoInst().getActivDocs();
		Map<String, String> docs1 = new HashMap<String, String>();
		Iterator<Integer> docIter = docs.keySet().iterator();
		while(docIter.hasNext()){
			Integer ii = docIter.next();
			docs1.put(ii.toString(), docs.get(ii));
		}
		addToCache(DROPDOWN_DOCTORS, docs1);
	}
	
	static{
		Map<String, String> genders = new HashMap<String, String>();
		genders.put("M", "Male");
		genders.put("F", "Female");
		genders.put("O", "Not Specified");
		addToCache(DROPDOWN_GENDER, genders);
	}
	
	static{
		Map<String, String> conditions = new HashMap<String, String>();
		populateRandomDataByPrefix( conditions, 10, "Conditions ");
		addToCache(DROPDOWN_CONDITIONS, conditions);
		System.out.println( "Adding Conditions to cache..");
	}
	
	static{
		Map<String, String> allergyTypes = new HashMap<String, String>();
		populateRandomDataByPrefix(allergyTypes, 10, "Allergy ");
		addToCache(DROPDOWN_ALLERGY_TYPES, allergyTypes);
		System.out.println( "Adding allergyTypes to cache..");
	}
	
	static{
		Map<String, String> dummyData = new HashMap<String, String>();
		populateRandomDataByPrefix(dummyData, 10, "dummyData ");
		addToCache(DROPDOWN_DUMMY_DATA, dummyData);
		System.out.println( "Adding dummyData to cache..");
	}
	static{
		Map<String, String> familyRelationShips = new HashMap<String, String>();
		familyRelationShips.put("1", "Mother");
		familyRelationShips.put("2", "Father");
		familyRelationShips.put("3", "Uncle");
		familyRelationShips.put("4", "GrandFather");
		addToCache(DROPDOWN_FAMILY_RELATIONSHIPS, familyRelationShips);
		System.out.println( "Adding familyRelationShips to cache..");
	}
	
	static{
		Map<String, String> familyRelativeStatus = new HashMap<String, String>();
		familyRelativeStatus.put("1", "Alive");
		familyRelativeStatus.put("2", "Deceased");
		familyRelativeStatus.put("3", "Unknown");
		familyRelativeStatus.put("4", "Not Alive");
		addToCache(DROPDOWN_FAMILY_RELATIVE_STATUS, familyRelativeStatus);
		System.out.println( "Adding familyRelativeStatus to cache..");
	}
	static{
		Map<String, String> socialHistoryName = new HashMap<String, String>();
		socialHistoryName.put("1", "Smoking");
		socialHistoryName.put("2", "Alcohol");
		socialHistoryName.put("3", "On Insulin Medication");
		socialHistoryName.put("4", "On Regular Exercise");
		addToCache(DROPDOWN_SOCIAL_HISTORY_NAMES, socialHistoryName);
		System.out.println( "Adding socialHistoryName to cache..");
	}
	
	static{
		Map<String, String> hpiTitles = new HashMap<String, String>();
		hpiTitles.put("1", "Diabetis");
		hpiTitles.put("2", "Cough");
		hpiTitles.put("3", "Cold");
		hpiTitles.put("4", "Headache");
		addToCache(DROPDOWN_HPI_TITLES, hpiTitles);
		System.out.println( "Adding hpiTitles to cache..");
	}
	
	static{
		Map<String, String> subjectiveSystems = new HashMap<String, String>();
		subjectiveSystems.put("1", "HEENT");
		subjectiveSystems.put("2", "Cardiovascular");
		subjectiveSystems.put("3", "ECG");
		subjectiveSystems.put("4", "UltraSound");
		addToCache(DROPDOWN_SUBJECTIVE_SYSTEMS, subjectiveSystems);
		System.out.println( "Adding subjectiveSystems to cache..");
	}
	
	static{
		Map<String, String> immunizations = new HashMap<String, String>();
		immunizations.put("1", "Influenza");
		immunizations.put("2", "Vitamin B12");
		immunizations.put("3", "Vitamin B6");
		immunizations.put("4", "Tetanus");
		addToCache(DROPDOWN_IMMUNIZATIONS, immunizations);
		System.out.println( "Adding immunizations to cache..");
	}
	
	static{
		Map<String, String> injectionLocations = new HashMap<String, String>();
		injectionLocations.put("1", "Left Deltoid");
		injectionLocations.put("2", "Right Thigh");
		injectionLocations.put("3", "Left Thigh");
		injectionLocations.put("4", "Right Buttocks");
		injectionLocations.put("5", "Left Buttocks");
		addToCache(DROPDOWN_INJECTION_LOCATIONS, injectionLocations);
		System.out.println( "Adding injectionLocations to cache..");
	}
	
	static{
		Map<String, String> billingCodes = new HashMap<String, String>();
		billingCodes.put("1", "123321");
		billingCodes.put("2", "234322");
		billingCodes.put("3", "345534");
		billingCodes.put("4", "498799");
		billingCodes.put("5", "512332");
		addToCache(DROPDOWN_BILLING_CODES, billingCodes);
		System.out.println( "Adding billingCodes to cache..");
	}
	
	private static void populateRandomDataByPrefix( Map<String, String> ds, int howMany, String prefix ) {
		for(int i=0;i<howMany;i++){
			ds.put(""+i, ""+RandomStringer.getRandomStringusingPrefix(prefix, 20)+"");
		}
	}
	
	public static void main(String[] args) {
		System.out.println( BaseCache.cache.get(DROPDOWN_CONDITIONS) );
		System.out.println( BaseCache.cache.get(DROPDOWN_APPOINTMENT_SLOTS) );
	}
}
