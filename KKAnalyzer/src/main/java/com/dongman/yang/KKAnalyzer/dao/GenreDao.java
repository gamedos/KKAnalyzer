package com.dongman.yang.KKAnalyzer.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yang.park.utils.JSQLHelper;
import com.yang.park.utils.JStringUtils;
import com.yang.park.utils.MysqlUtils;

public class GenreDao {
	private static GenreDao instance;
	private String tableName = "tbGenre";
	//
	public static GenreDao getInstance(){
		if(instance == null){
			instance = new GenreDao();
		}
		return instance;
	}
	//
	public String getAllGid(){
		JSQLHelper sqlHelper = new JSQLHelper(this.tableName, 200);
		List<String> gidArr = new ArrayList<String>();
		//
		sqlHelper.setFields(new String[]{"gid","gtitle"});
		String sqlQ = sqlHelper.getSqlQuery();
		List<Map<String,Object>> rs = MysqlUtils.getAll(sqlQ);
		for(int i=0,l=rs.size();i<l;i++){
			gidArr.add( ""+rs.get(i).get("gid") );
		}
		return JStringUtils.join(gidArr, ",");
	}
	//
	
	
}
