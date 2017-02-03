package com.yang.park.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils{

	public static int getTime(){
		long current = System.currentTimeMillis();
		int time = (int)(current / 1000 );
		return time;
	}
	
	public static String getYYYYMMDD(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		long time = System.currentTimeMillis();
		return simpleDateFormat.format(time);
	}
	
	public static String getYestoday(){
		Date as = new Date(new Date().getTime()-86400*1000);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		return simpleDateFormat.format(as);
	}
	
}