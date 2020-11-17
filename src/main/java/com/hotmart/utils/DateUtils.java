package com.hotmart.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;

public class DateUtils {
	
	public String getDateFormatted(Date date, String format) {
		
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
		
	}
	
	public Date addDaysDate(Date date, Integer days) {
		
		DateTime dateTime = new DateTime(date);
		DateTime dateTimePlusDay = dateTime.plusDays(1);
		return dateTimePlusDay.toDate();
		
	}

}
