package com.fort.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Calendar;

import static java.util.Calendar.YEAR;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.DATE;

public class DateUtils {
	private static Date today;
	public static Date today(){
		if( today == null )
			today = new Date( Calendar.getInstance().getTime().getTime() );
		return today;
	}
	public static int getDiffYears(java.sql.Date first, java.sql.Date date) {
		Calendar a = getCalendar(first);
		Calendar b = getCalendar(date);
		int diff = b.get(YEAR) - a.get(YEAR);
		if (a.get(MONTH) > b.get(MONTH)
				|| (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
			diff--;
		}
		return diff;
	}

	public static int getAgeFromDob( java.sql.Date dob ){
		return getDiffYears(dob, today());
	}
	
	public static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static Date stringToDateConvert(String dateStr) {
		Date date = null;
		if (dateStr != null) {
			String format = "yyyy-MM-dd";
			try {
				System.out.println("attempting to parse date: " + dateStr);
				date = new Date( new SimpleDateFormat(format).parse(dateStr).getTime() );
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return date;
	}

	public static String dateToStringConvert( Date date, String format ) {
		return new SimpleDateFormat(format).format(date);
	}
	
	public static String dateToStringConvert( Date date ) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	public static void main(String[] args) throws ParseException {
		String format = "yyyy-MM-dd";
		String dateStr = "2013-12-15";
		Date date = new Date( new SimpleDateFormat(format).parse(dateStr).getTime() );
		System.out.println(date );
		
		format = "yyyy-MM-dd hh:mm a";
		System.out.println( new SimpleDateFormat(format).format(Calendar.getInstance().getTime()));
	}
}
