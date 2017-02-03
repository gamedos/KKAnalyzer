package com.dongman.yang.KKAnalyzer.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TopicHistory {

		private String hisid;
		private int id;
		private int gid;
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
		public TopicHistory(){
			
		}
		/**
		 * @param id
		 * @param likesCount
		 * @param hotCount
		 * @param commentCount
		 */
		public TopicHistory(int id, int gid, long likesCount, long hotCount, long commentCount, int timetime,String timeDate , Calendar  calendar) {
			this.id = id;
			this.gid = gid;
			this.likesCount = likesCount;
			this.hotCount = hotCount;
			this.commentCount = commentCount;

			this.likesCountToday = 0;
			this.hotCountToday = 0;
			this.commentCountToday = 0;

			this.timeYear = calendar.get(calendar.YEAR);
			this.timeMonth = calendar.get(calendar.MONTH)+1;
			this.timeDay  = calendar.get(calendar.DAY_OF_MONTH);
			this.timetime = timetime;
			this.timeDate = timeDate;

			this.hisid = this.id + "-" + this.timeDate;
		}
		//
		public void setOldData(Map<String,Object> oldData){
			if(null == oldData) return;

//			long c = ((BigDecimal)oldData.get("commentCount")).longValue();
//			long l = ((BigDecimal)oldData.get("likesCount")).longValue();
//			long h= ((BigDecimal)oldData.get("hotCount")).longValue();
			long c = (long)oldData.get("commentCount");
			long l = (long)oldData.get("likesCount");
			long h= (long)oldData.get("hotCount");			
			
			this.likesCountToday = this.likesCount - l;
			this.hotCountToday = this.hotCount - h;
			this.commentCountToday = this.commentCount - c;
			

			if(this.likesCountToday<0) this.likesCountToday = 0;
			if(this.hotCountToday<0) this.hotCountToday = 0;
			if(this.commentCountToday<0) this.commentCountToday = 0;
			
		}
		//
		public Map<String, Object> getMapData(){

			Map<String,Object> data = new HashMap<String,Object>();
			data.put("hisid", this.hisid);
			data.put("id", this.id);
			data.put("gid", this.gid);
			data.put("likesCount", this.likesCount);
			data.put("hotCount", this.hotCount);
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
		//
		public String getHisid() {
			return hisid;
		}
		public void setHisid(String hisid) {
			this.hisid = hisid;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public long getLikesCount() {
			return likesCount;
		}
		public void setLikesCount(int likesCount) {
			this.likesCount = likesCount;
		}
		public long getHotCount() {
			return hotCount;
		}
		public void setHotCount(int hotCount) {
			this.hotCount = hotCount;
		}
		public long getCommentCount() {
			return commentCount;
		}
		public void setCommentCount(int commentCount) {
			this.commentCount = commentCount;
		}
		public int getTimeYear() {
			return timeYear;
		}
		public void setTimeYear(int timeYear) {
			this.timeYear = timeYear;
		}
		public int getTimeMonth() {
			return timeMonth;
		}
		public void setTimeMonth(int timeMonth) {
			this.timeMonth = timeMonth;
		}
		public int getTimeDay() {
			return timeDay;
		}
		public void setTimeDay(int timeDay) {
			this.timeDay = timeDay;
		}
		public int getGid() {
			return gid;
		}
		public void setGid(int gid) {
			this.gid = gid;
		}
		public void setLikesCount(long likesCount) {
			this.likesCount = likesCount;
		}
		public void setHotCount(long hotCount) {
			this.hotCount = hotCount;
		}
		public void setCommentCount(long commentCount) {
			this.commentCount = commentCount;
		}
		public int getTimetime() {
			return timetime;
		}
		public void setTimetime(int timetime) {
			this.timetime = timetime;
		}
		public String getTimeDate() {
			return timeDate;
		}
		public void setTimeDate(String timeDate) {
			this.timeDate = timeDate;
		}
		
}
