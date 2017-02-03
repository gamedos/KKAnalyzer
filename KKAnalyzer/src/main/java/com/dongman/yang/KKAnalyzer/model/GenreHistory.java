package com.dongman.yang.KKAnalyzer.model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GenreHistory {

	private String hisid;
	private int gid;
	private int topicCount;
	private long likesCount;
	private long hotCount;
	private long commentCount;
	private int timeYear;
	private int timeMonth;
	private int timeDay;
	private int timetime;
	private String timeDate;
	//
	public GenreHistory(){
		
	}
	public  GenreHistory( int gid, long likesCount, long hotCount, long commentCount,int topicCount, int timetime,String timeDate , Calendar  calendar) {
		this.gid = gid;
		this.likesCount = likesCount;
		this.hotCount = hotCount;
		this.commentCount = commentCount;
		this.topicCount = topicCount;

		this.timeYear = calendar.get(calendar.YEAR);
		this.timeMonth = calendar.get(calendar.MONTH)+1;
		this.timeDay  = calendar.get(calendar.DAY_OF_MONTH);
		this.timetime = timetime;
		this.timeDate = timeDate;

		this.hisid = this.gid + "-" + this.timeDate;
	}
	//
	public Map<String, Object> getMapData(){

		Map<String,Object> data = new HashMap<String,Object>();
		data.put("hisid", this.hisid);
		data.put("gid", this.gid);
		data.put("likesCount", this.likesCount);
		data.put("hotCount", this.hotCount);
		data.put("topicCount", this.topicCount);
		data.put("commentCount", this.commentCount);
		data.put("timeYear", this.timeYear);
		data.put("timeMonth", this.timeMonth);
		data.put("timeDay", this.timeDay);
		data.put("timetime", this.timetime);
		data.put("timeDate", this.timeDate);
		
		return data;
	}
	
	
	
}
