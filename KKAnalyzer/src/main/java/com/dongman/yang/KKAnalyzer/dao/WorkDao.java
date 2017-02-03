package com.dongman.yang.KKAnalyzer.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.dongman.yang.KKAnalyzer.model.Work;
import com.yang.park.utils.DateTimeUtils;
import com.yang.park.utils.MysqlUtils;

public class WorkDao {
	private static WorkDao instance;
	private String tableName = "tbWork";
	//
	public static WorkDao getInstance(){
		if(instance == null){
			instance = new WorkDao();
		}
		return instance;
	}
	/**
	 * 获取今日work信息，
	 * 1，是否创建，如果没有创建就创建任务
	 * 2，如果已经创建，返回详细信息
	 */
	public Work getTodayWork(){
		int wid = this.getWID();
		Work work = new Work() ;
		//
		Map<String, Object> data = MysqlUtils.someFieldsValues(this.tableName, new String[]{"wid","wstate","windex","createtime","endtime","temp"}, "wid="+wid);
		if(data == null){
			// 创建新数据
			work.setWid(wid);
			work.setCreatetime(DateTimeUtils.getTime());
			MysqlUtils.insert(this.tableName, work.getMapData());
		}else{
			// 返回详细信息
			work.setMapData(data);
		}
		return work;
	}
	/**
	 * 
	 * @param windex
	 * @param wid
	 */
	public void setIndex(Integer windex , Integer wid){
		Map<String,Object> data = new HashMap<>();
		data.put("windex", windex);
		MysqlUtils.update(this.tableName, data, " wid =" + wid);
	}
	/**
	 * 改变任务状态
	 */
	public void setState(Integer newState , Integer wid){
		Map<String,Object> data = new HashMap<>();
		data.put("wstate", newState);
		MysqlUtils.update(this.tableName, data, " wid =" + wid);
	}
	/**
	 * 改变任务 备注信息
	 * @param newTemp
	 * @param wid
	 */
	public void setTemp(String newTemp, Integer wid){
		Map<String,Object> data = new HashMap<>();
		data.put("temp", newTemp);
		MysqlUtils.update(this.tableName, data, " wid =" + wid);
	}
	
	//
	private int getWID(){
		Date today = new Date();
		SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
		String sWid = yyyyMMdd.format(today);
		return Integer.parseInt(sWid);
	}
}
