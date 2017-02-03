/**    
 **	@author		yang.park@dongmancorp.com
 **	@date			2017年2月3日 下午3:57:36 * 
 **	@version	1.0
*/
package com.dongman.yang.KKAnalyzer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.configuration.Configuration;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dongman.yang.KKAnalyzer.service.WorkService;
import com.yang.park.utils.MysqlUtils;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:beans.xml")
public class MainProc {

//	public static ScheduledExecutorService service;

	public static void main(String[] args) {
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
		 WorkTask workTask = (WorkTask)context.getBean("WorkTask");
		 workTask.getService();
//		//
//		Runnable runable = new Runnable() {
//			@Override
//			public void run() {
//				WorkService workService = new WorkService();
//				int state = workService.checkWork();
//				if (state == WorkService.WORK_STATE_END) {
//					MysqlUtils.close();
//					service.shutdown();
//				}
//			}
//		};
//		//
//		service = Executors.newSingleThreadScheduledExecutor();
//		service.scheduleAtFixedRate(runable, 0, 3, TimeUnit.SECONDS);

	}

}
