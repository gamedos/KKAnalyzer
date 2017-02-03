package com.dongman.yang.KKAnalyzer.service.workers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import com.dongman.yang.KKAnalyzer.dao.WorkDao;
import com.dongman.yang.KKAnalyzer.model.SiteHistory;
import com.dongman.yang.KKAnalyzer.model.Work;
import com.dongman.yang.KKAnalyzer.service.WorkService;
import com.yang.park.utils.DateTimeUtils;
import com.yang.park.utils.MysqlUtils;

public class AnalysisWoker {
	private static AnalysisWoker instance;
	public static AnalysisWoker getInstance(){
		if(instance == null){
			instance = new AnalysisWoker();
		}
		return instance;
	}

	public int dojob(Work work){
		Calendar calendar = Calendar.getInstance();  
		int timetime = DateTimeUtils.getTime();
		String timeDate = DateTimeUtils.getYYYYMMDD();
		
		String sql = "SELECT count(id) as ttopic,"
				+ " sum(likesCount) as tlikes,sum(hotCount) as thot,sum(commentCount) as tcomment ,"
				+ " sum(likesCountToday) as tlikesToday,sum(hotCountToday) as thotToday,sum(commentCountToday) as tcommentToday "
				+ " FROM tbtopichistory WHERE timeDate='"+timeDate + "'";
		System.out.println(sql);
		ArrayList<Map<String,Object>> datas = (ArrayList<Map<String,Object>>)MysqlUtils.getAll(sql);
		int max = datas.size();
		if(max>0){
			Map<String,Object> data = datas.get(0);
			
			//
			long ttopic = (long)data.get("ttopic");
			int topicCount =  (int)ttopic;
			
			long commentCount = ((BigDecimal)data.get("tcomment")).longValue();
			long likesCount = ((BigDecimal)data.get("tlikes")).longValue();
			long hotCount = ((BigDecimal)data.get("thot")).longValue();
			
			long commentCountToday = ((BigDecimal)data.get("tcommentToday")).longValue();
			long likesCountToday = ((BigDecimal)data.get("tlikesToday")).longValue();
			long hotCountToday = ((BigDecimal)data.get("thotToday")).longValue();			

			SiteHistory siteHistory = new SiteHistory(
					likesCount, hotCount, commentCount,
					likesCountToday, hotCountToday, commentCountToday,
					topicCount,timetime,timeDate, calendar);
			
			MysqlUtils.insert("tbsitehistory", siteHistory.getMapData());	
		}
		// 改变状态 今日任务完成
		WorkDao.getInstance().setState(WorkService.WORK_STATE_END, work.getWid());
		return max;
	}
}
