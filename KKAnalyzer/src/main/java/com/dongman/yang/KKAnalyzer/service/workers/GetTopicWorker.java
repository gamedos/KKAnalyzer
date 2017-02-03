package com.dongman.yang.KKAnalyzer.service.workers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.configuration.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dongman.yang.KKAnalyzer.dao.GenreDao;
import com.dongman.yang.KKAnalyzer.dao.WorkDao;
import com.dongman.yang.KKAnalyzer.model.Topic;
import com.dongman.yang.KKAnalyzer.model.Work;
import com.dongman.yang.KKAnalyzer.service.WorkService;
import com.yang.park.utils.JStringUtils;
import com.yang.park.utils.JacksonUtils;
import com.yang.park.utils.MysqlUtils;

@Service
public class GetTopicWorker {

	@Resource(name = "commonConfig")
	private Configuration commonConfig;
	
//	private static GetTopicWorker instance;
//	
//	//
//	public static GetTopicWorker getInstance(){
//		if(instance == null){
//			instance = new GetTopicWorker();
//		}
//		return instance;
//	}
	//
	public int getTopics(Work work){
		String strGenre = work.getTemp();
		System.out.println("strGenre" + strGenre);
		if(null == strGenre || "".equals(strGenre)){
			return 0;
		}
		//
		int realcount = 0;
		String[] arrGenre = strGenre.split(",");
		List<String> listGenre = new ArrayList<String>(Arrays.asList(arrGenre));
		int genreid = Integer.parseInt(listGenre.get(0));
		int curpage = work.getWindex();
		int getCount = 50;

		//
//		String url = "http://www.kkmh.com/web/tags/{tags}?count={count}&page={page}";
		String url = commonConfig.getString("worker.getTopic.url");
		//
		RestTemplate restTemplate = new RestTemplate();
		String json = (String) restTemplate.getForObject(url, String.class, new Object[] { genreid, ""+getCount, ""+curpage });
		Map obj = (Map) JacksonUtils.toObject(json, Object.class);
		String message = (String) obj.get("message");
		if ("OK".equals(message)) {
			Map data = (Map) obj.get("data");
			List<Map> topics = (List<Map>) data.get("topics");
			realcount = topics.size();
			if (realcount > 0) {
				for (Map m : topics) {
					m.put("gid", genreid);//获取的数据里没这项
					Topic topic = new Topic(m);
					MysqlUtils.insert("tbTopics", topic.getMapData());	
				}
			}
		}
		//可以判定没有更多
		if(realcount<getCount){
			listGenre.remove(0);
			//没有跟多genre时 表示topic获取任务结束
			if(listGenre.size()<1){
				WorkDao.getInstance().setState(WorkService.WORK_STATE_UPDATE_INFOS, work.getWid());
				WorkDao.getInstance().setIndex(0, work.getWid());
				WorkDao.getInstance().setTemp("", work.getWid());
			}else{
				// 读取page 从0 开始
				WorkDao.getInstance().setIndex(0, work.getWid());
				// 把剩余genre保存 
				WorkDao.getInstance().setTemp(JStringUtils.join(listGenre, ","), work.getWid());
			}
		}else{
			//没读完 只是page 向后走
			curpage++;
			WorkDao.getInstance().setIndex(curpage, work.getWid());
		}
		 
		
		
		return realcount;
	}
}
