/**    
 **	@author		yang.park@dongmancorp.com
 **	@date			2017年2月3日 下午3:30:28 * 
 **	@version	1.0
*/
package com.dongman.yang.KKAnalyzer.service;

import com.dongman.yang.KKAnalyzer.dao.GenreDao;
import com.dongman.yang.KKAnalyzer.dao.TopicTagsDao;
import com.dongman.yang.KKAnalyzer.dao.WorkDao;
import com.dongman.yang.KKAnalyzer.model.Work;
import com.dongman.yang.KKAnalyzer.service.workers.AnalysisWoker;
import com.dongman.yang.KKAnalyzer.service.workers.GetInfoWorker;
import com.dongman.yang.KKAnalyzer.service.workers.GetTopicWorker;
import com.yang.park.utils.DateTimeUtils;

public class WorkService {
	//
	public static int WORK_STATE_CREATE = 0;
	public static int WORK_STATE_UPDATE_TOPICS = 1;
	public static int WORK_STATE_UPDATE_INFOS = 2;
	public static int WORK_STATE_ANALYSIS= 3;
	public static int WORK_STATE_END=4;
	//
	private Work currentWork;
	// 
	public WorkService(){
	}
	
	public int checkWork(){
		currentWork = WorkDao.getInstance().getTodayWork();
		System.out.println("" + currentWork.toString());
		int currentState = currentWork.getWstate();
		int realCount =  0;
		if(currentState == WORK_STATE_CREATE){
			// 改变状态为更新topic
			System.out.println("今日任务开始啦 --- " + DateTimeUtils.getYYYYMMDD());
			WorkDao.getInstance().setState(WORK_STATE_UPDATE_TOPICS, currentWork.getWid());
			WorkDao.getInstance().setTemp(GenreDao.getInstance().getAllGid(), currentWork.getWid());
			
			TopicTagsDao.getInstance().clear();//清楚标签数据
			// 调用获取获取topic任务
			System.out.println("调用获取获取topic任务");
			realCount = GetTopicWorker.getInstance().getTopics(currentWork);
			System.out.println("realCount => " + realCount);
		}else if(currentState == WORK_STATE_UPDATE_TOPICS){
			// 调用获取获取topic任务
			System.out.println("调用获取获取topic任务");
			realCount = GetTopicWorker.getInstance().getTopics(currentWork);
			System.out.println("realCount => " + realCount);
		}else if(currentState == WORK_STATE_UPDATE_INFOS){
			System.out.println("获取详细信息任务");
			realCount = GetInfoWorker.getInstance().getInfo(currentWork);
			System.out.println("realCount => " + realCount);
		}else if(currentState == WORK_STATE_ANALYSIS){
			System.out.println("统计今日数据");
			realCount = AnalysisWoker.getInstance().dojob(currentWork);
			System.out.println("realCount => " + realCount);
		}else if(currentState == WORK_STATE_END){
			System.out.println("今日任务结束啦 --- " + DateTimeUtils.getYYYYMMDD());
		}
		return currentState;
	}
}