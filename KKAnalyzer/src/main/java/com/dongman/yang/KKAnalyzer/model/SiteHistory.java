package com.dongman.yang.KKAnalyzer.model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SiteHistory {

	private int hisid;
	private int topicCount;
	private long likesCount;
	private long hotCount;
	private long commentCount;

	private long likesCountToday;
	private long hotCountToday;
	private long commentCountToday;
	
	private int timeYear;
	private int timeMonth;
	private int timeDay;
	private int timetime;
	private String timeDate;
	//
	public SiteHistory(){
		this.topicCount = 0;
		this.likesCount = 0;
		this.hotCount = 0;
		this.commentCount = 0;
	}
	public SiteHistory(int timetime,String timeDate , Calendar  calendar){
		this.topicCount = 0;
		this.likesCount = 0;
		this.hotCount = 0;
		this.commentCount = 0;
		
		this.timeYear = calendar.get(calendar.YEAR);
		this.timeMonth = calendar.get(calendar.MONTH)+1;
		this.timeDay  = calendar.get(calendar.DAY_OF_MONTH);
		this.timetime = timetime;
		this.timeDate = timeDate;

		this.hisid = Integer.parseInt(this.timeDate);
	}
	public SiteHistory(  long likesCount, long hotCount, long commentCount,
			long likesCountToday, long hotCountToday, long commentCountToday,
			int topicCount, int timetime,String timeDate , Calendar  calendar ) {
		
		this.topicCount = topicCount;
		
		this.likesCount = likesCount;
		this.hotCount = hotCount;
		this.commentCount = commentCount;
		
		this.likesCountToday = likesCountToday;
		this.hotCountToday = hotCountToday;
		this.commentCountToday = commentCountToday;
		
		if(this.likesCountToday<0) this.likesCountToday = 0;
		if(this.hotCountToday<0) this.hotCountToday = 0;
		if(this.commentCountToday<0) this.commentCountToday = 0;
		
		this.timeYear = calendar.get(calendar.YEAR);
		this.timeMonth = calendar.get(calendar.MONTH)+1;
		this.timeDay  = calendar.get(calendar.DAY_OF_MONTH);
		this.timetime = timetime;
		this.timeDate = timeDate;

		this.hisid = Integer.parseInt(this.timeDate);
	}
	//
	public Map<String, Object> getMapData(){

		Map<String,Object> data = new HashMap<String,Object>();
		data.put("hisid", this.hisid);
		data.put("likesCount", this.likesCount);
		data.put("hotCount", this.hotCount);
		data.put("topicCount", this.topicCount);
		data.put("commentCount", this.commentCount);

		data.put("likesCountToday", this.likesCountToday);
		data.put("hotCountToday", this.hotCountToday);
		data.put("commentCountToday", this.commentCountToday);
		
		data.put("timeYear", this.timeYear);
		data.put("timeMonth", this.timeMonth);
		data.put("timeDay", this.timeDay);
		data.put("timetime", this.timetime);
		data.put("timeDate", this.timeDate);
		
		return data;
	}
	
	
	
}
