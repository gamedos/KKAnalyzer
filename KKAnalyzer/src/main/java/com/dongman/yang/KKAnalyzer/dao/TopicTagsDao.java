package com.dongman.yang.KKAnalyzer.dao;

import java.util.HashMap;
import java.util.Map;

import com.yang.park.utils.MysqlUtils;

public class TopicTagsDao {
	private static TopicTagsDao instance;
	private String tableName = "tbTopicTags";
	
	public static TopicTagsDao getInstance(){
		if(instance == null){
			instance = new TopicTagsDao();
		}
		return instance;
	}
	/**
	 * 清空(每日收集数据前清空一下，只保留最终数据)
	 */
	public void clear(){
		MysqlUtils.truncate("tbTopicTags");
	}
	//
	public void addData(int topicId, int tagId){
		String hkey = tagId + "-" + topicId;
		Map<String,Object> data = new HashMap<>();
		data.put("topicid", topicId);
		data.put("tagid", tagId);
		data.put("hkey", hkey);
		MysqlUtils.insert(tableName, data);
	}
}
