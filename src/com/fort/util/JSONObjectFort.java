package com.fort.util;

import java.sql.Date;

import org.json.JSONException;
import org.json.JSONObject;
import static com.fort.consts.CommonConstants.DATE_FORMAT_MM_DD_YYYY;

public class JSONObjectFort extends JSONObject {
	public JSONObject put(String key, Object value) throws JSONException {
        if (key != null && value != null) {
            if (this.opt(key) != null) {
                throw new JSONException("Duplicate key \"" + key + "\"");
            }
          	if( value instanceof Date )
          		this.put(key, DateUtils.dateToStringConvert((Date)value, DATE_FORMAT_MM_DD_YYYY));
          	else
          		super.put(key, value );
        }
        return this;
    }
}
