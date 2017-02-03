package com.dongman.yang.KKAnalyzer.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.yang.park.utils.MysqlUtils;

public class TopicsDao {
	private static TopicsDao instance;
	private String tableName = "tbTopics";
	
	
	public static TopicsDao getInstance(){
		if(instance == null){
			instance = new TopicsDao();
		}
		return instance;
	}
	
	/**
	 * 需要获取详细信息的id列表
	 */
	public Map<String,Object> getNeedInfoId(int day){
		//
		Integer id = MysqlUtils.oneIntegerValue(this.tableName, "id", " updateDay<>"+day);
		Map<String,Object> data = MysqlUtils.someFieldsValues(this.tableName, new String[]{"id", "gid"}, " updateDay<>"+day );
		return data;
	}
	/**
	 * 更新获取信息日期， 表示已获取过数据
	 * @param id
	 * @param day
	 */
	public void setUpdateDay(int id,int day){
		Map<String,Object> data = new HashMap<>();
		data.put("updateDay", day);
		MysqlUtils.update(this.tableName, data, " id="+id);
	}
	
}
