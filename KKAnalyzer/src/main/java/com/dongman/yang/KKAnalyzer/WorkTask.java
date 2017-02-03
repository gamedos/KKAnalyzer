/**    
 **	@author		yang.park@dongmancorp.com
 **	@date			2017年2月3日 下午7:42:22 * 
 **	@version	1.0
*/
package com.dongman.yang.KKAnalyzer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dongman.yang.KKAnalyzer.service.WorkService;
import com.yang.park.utils.MysqlUtils;

@Service
public class WorkTask {

	@Resource(name = "jdbcConfig")
	private Configuration jdbcConfig;
	
	@Autowired
	private WorkService workService;
	
	public ScheduledExecutorService service;
	//
	Runnable runable = new Runnable() {
		@Override
		public void run() {
			int state = workService.checkWork();
			if (state == WorkService.WORK_STATE_END) {
				MysqlUtils.close();
				service.shutdown();
				System.out.println("--End.--");
			}
		}
	};
	//
	public ScheduledExecutorService getService(){

		String driver = jdbcConfig.getString("mysql.driver");
		String url = jdbcConfig.getString("mysql.service.url");
		String user= jdbcConfig.getString("mysql.user");
		String password= jdbcConfig.getString("mysql.password");
		MysqlUtils.initJDBC(driver, url, user, password);
		
		service = Executors.newSingleThreadScheduledExecutor();
//		service.scheduleAtFixedRate(runable, 0, 3, TimeUnit.SECONDS);
		service.scheduleWithFixedDelay(runable, 1, 1, TimeUnit.SECONDS);
		return service;
	}
	
	
	// 5s
//	@Scheduled(fixedDelay = 5000)
//	public void doWork(){
//		System.out.println("hello");
//	}
}
