package com.m2m.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {

	private static Calendar getResetCalendar(){
		Calendar calendar = Calendar.getInstance();
		long millis = calendar.getTimeInMillis();
		calendar.setTimeInMillis(millis - millis%(24*60*60*1000) - TimeZone.getDefault().getRawOffset());
		return calendar;
	}
	public static Date getStartOfToday(){
		Calendar calendar = getResetCalendar();
		return calendar.getTime();
	}

	public static Date getStartOfTomorrow(){
		Calendar calendar = getResetCalendar();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		return calendar.getTime();
	}

    public static Date getStartOfYesterday(){
        Calendar calendar = getResetCalendar();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }

	public static String date2string(Date date, String pattern){
		return date2string(date, pattern, null);
	}
	
	public static Date string2date(String dateStr, String pattern) throws ParseException{
		return string2date(dateStr, pattern, null);
	}
	
	public static String date2string(Date date, String pattern, Locale locale){
		if(null == date || null == pattern){
			return "";
		}
		SimpleDateFormat sdf = null;
		if(null == locale){
			sdf = new SimpleDateFormat(pattern);
		}else{
			sdf = new SimpleDateFormat(pattern, locale);
		}
		return sdf.format(date);
	}
	
	public static Date string2date(String dateStr, String pattern, Locale locale) throws ParseException{
		if(null == dateStr || null == pattern){
			return null;
		}
		SimpleDateFormat sdf = null;
		if(null == locale){
			sdf = new SimpleDateFormat(pattern);
		}else{
			sdf = new SimpleDateFormat(pattern, locale);
		}
		try{
			return sdf.parse(dateStr);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean isSameDay(Date date1, Date date2){
		if(null == date1 || null == date2){
			return false;
		}
		String datestr1 = date2string(date1, "yyyyMMdd");
		String datestr2 = date2string(date2, "yyyyMMdd");
		if(datestr1.equals(datestr2)){
			return true;
		}
		return false;
	}
	
	public static Date addDay(Date date, int dayNum){
		if(null == date){
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, dayNum);
		return cal.getTime();
	}
	
	public static Date addHour(Date date, int hourNum){
		if(null == date){
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, hourNum);
		return cal.getTime();
	}
	
	public static long getDaysBetween2Date(Date date1, Date date2){
		if(null == date1 || null == date2){
			return -1;
		}
		try{
			date1 = string2date(date2string(date1, "yyyy-MM-dd"), "yyyy-MM-dd");
			date2 = string2date(date2string(date2, "yyyy-MM-dd"), "yyyy-MM-dd");
			long day = (date1.getTime() - date2.getTime())/(24*60*60*1000l);
			return Math.abs(day);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}
	
	public static long getHoursBetween2Date(Date date1, Date date2){
		if(null == date1 || null == date2){
			return -1;
		}
		try{
			date1 = string2date(date2string(date1, "yyyy-MM-dd HH:mm"), "yyyy-MM-dd HH:mm");
			date2 = string2date(date2string(date2, "yyyy-MM-dd HH:mm"), "yyyy-MM-dd HH:mm");
			long hour = (date1.getTime() - date2.getTime())/(60*60*1000l);
			return Math.abs(hour);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}
	/** 
	*字符串的日期格式的计算 
	*/  
	    public static int daysBetween(String smdate,String bdate) throws ParseException{  
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	        Calendar cal = Calendar.getInstance();    
	        cal.setTime(sdf.parse(smdate));    
	        long time1 = cal.getTimeInMillis();                 
	        cal.setTime(sdf.parse(bdate));    
	        long time2 = cal.getTimeInMillis();         
	        long between_days=(time2-time1)/(1000*3600*24);  
	            
	       return Integer.parseInt(String.valueOf(between_days));     
	    }
}
