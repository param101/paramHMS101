package com.fort.common;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fort.cache.BaseCache;
import com.fort.util.JSONObjectFort;

public class LookAheadAction implements RequestAware{
	private static final int LIMIT=50;
	
	private String q; // query
	private Integer t; // type; for ex: ICD Codes, Symptoms, etc
	private boolean idx; //search by index (for ex: search by icd code + description as user can search by both)
	
	public String execute() throws JSONException{
		if( q!=null && q.length() >0 && t != null && t != 0 ){
			System.out.println("input: " + q);
			Map<String,String> data = BaseCache.getCache(t);
			
			JSONObject jsonObj = new JSONObjectFort();
			JSONArray jsonArray = new JSONArray();
			
			JSONObject json = new JSONObjectFort();
			
			Iterator<String> iter = data.keySet().iterator();
			int limitController = 0;
			while(iter.hasNext()){
				String n = iter.next();
				
				if(q.contains(" ")){
					String[] qq = q.split(" ");
					boolean allThere=true;
					for(String qqq: qq )
						if(allThere && ! ( ! json.has(n) && doesItFilter(qqq, n, data.get(n), idx) ) )
							allThere=false;
					if(allThere){
						json.put(n, data.get(n));
						limitController++;
					}
					
				} else {
					if(doesItFilter(q,n,data.get(n),idx)){
						json.put(n, data.get(n));
						limitController++;
					}
				}
				if( limitController >= LIMIT)
					break;
			}
			jsonArray.put(json);
			System.out.println( "size of the result array: " + limitController);
			jsonObj.put("aaData", jsonArray);
			getRequest().put("jsonData", jsonObj);
		} else {
			System.out.println( "unknown type: " + this );
		}
		return "json-data";
	}

	private boolean doesItFilter(String q, String index, String value, boolean searchByIndex){
		return value.toLowerCase().contains(q.toLowerCase()) || (searchByIndex && index.toString().toLowerCase().contains(q.toLowerCase()));
			
	}
	
	@Override
	public String toString() {
		final int maxLen = 10;
		return String.format("LookAheadAction [q=%s, t=%s, idx=%s, request=%s]", q, t, idx, request != null ? toString(request.entrySet(), maxLen) : null);
	}

	private String toString(Collection<?> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
			if (i > 0)
				builder.append(", ");
			builder.append(iterator.next());
		}
		builder.append("]");
		return builder.toString();
	}

	private Map<String, Object> request;
	
	@Override
	public void setRequest(Map r) {
		request = r;
	}

	public Map<String, Object> getRequest(){
		return request;
	}
	
	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public Integer getT() {
		return t;
	}

	public void setT(Integer t) {
		this.t = t;
	}

	public boolean isIdx() {
		return idx;
	}

	public void setIdx(boolean idx) {
		this.idx = idx;
	}
}
