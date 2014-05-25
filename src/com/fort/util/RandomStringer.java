package com.fort.util;

import java.util.UUID;

public class RandomStringer {
	public static String getRandomString(int length) {
		String randomStr = UUID.randomUUID().toString();
		while (randomStr.length() < length) {
			randomStr += UUID.randomUUID().toString();
		}
		return randomStr.substring(0, length);
	}
	public static String getRandomStringusingPrefix( String prefix, final int maxlen){
		prefix = prefix == null?"":prefix;
		if(prefix.length() >= maxlen )
			return prefix;
		return prefix + getRandomString( maxlen - prefix.length() );
	}
}
