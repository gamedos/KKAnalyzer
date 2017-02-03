/**    
 **	@author		yang.park@dongmancorp.com
 **	@date			2017年2月3日 下午3:57:36 * 
 **	@version	1.0
*/
package com.dongman.yang.KKAnalyzer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.dongman.yang.KKAnalyzer.service.WorkService;
import com.yang.park.utils.MysqlUtils;

public class MainProc {
	public static ScheduledExecutorService service;

	public static void main(String[] args) {   
		// TODO Auto-generated method stub
		
		Runnable runable = new Runnable() { 
			
			@Override
			public void run() {  
				WorkService workService = new WorkService();
				int state = workService.checkWork();
				if(state == WorkService.WORK_STATE_END){
					MysqlUtils.close();
					service.shutdown();
				}
			}
		};
		//
		service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(runable, 0, 3, TimeUnit.SECONDS);
	
	}

}
