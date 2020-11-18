package com.hotmart.constants;

public class JobConstants {
	
	//Search all pre-existing categories in API news
	public static final String API_NEWS_PATH_CATEGORY="?country=us&category=%s&apiKey=%s&from=%s&to=%s";

	//Additionally search for the category for the keyword
	public static final String API_NEWS_PATH_KEYWORD="?country=us&q=%s&apiKey=%s&from=%s&to=%s";
	
	public static final String DATE_FORMAT="yyyy-MM-dd";
	public static final String JOB_CRON_SCORE_BATCH="0 0 0/6 1/1 * ? *";//Every 6 hours
	
}
