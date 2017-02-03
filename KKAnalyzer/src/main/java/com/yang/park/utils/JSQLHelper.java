/**
 * @author Yang.Park
 * @Time    2017.12.10
 * JDBC 最笨的使用方案
 */
package com.yang.park.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JSQLHelper {

	private String tableName;
	private String[] fields;
	private List<String> condition;
	private List<String> orderby;

	private PageBase page;

	//
	public JSQLHelper() {
		this.tableName = "";
		this.fields = null;
		this.condition = null;
		this.orderby = null;
		this.page = new PageBase();
	}

	//
	public JSQLHelper(String tableName,int pageCount) {
		this.tableName = tableName;
		this.fields = null;
		this.condition = null;
		this.orderby = null;
		this.page = new PageBase(pageCount);
	}

	/**
	 * 获取当前条件下 返回数据列表的 sql
	 * @return
	 */
	public String getSqlQuery() {
		String sqlFields = "*";
		String sqlWhere = " ";
		String sqlOrderBy = " ";
		String sqlLimit = " ";
		//
		if (this.fields != null) {
			sqlFields = JStringUtils.join(this.fields, ",");
		}
		if (this.condition != null) {
			sqlWhere = " WHERE " + JStringUtils.join(this.condition, " AND ");
		}
		if (this.orderby != null) {
			sqlOrderBy = " ORDER BY " + JStringUtils.join(this.orderby, ",");
		}
		sqlLimit = " LIMIT " + this.page.getStartIndex() + " , " + this.page.getPageSize();
		//
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT ").append(sqlFields).append(" FROM ").append(this.tableName).append(sqlWhere)
				.append(sqlOrderBy).append(sqlLimit);
		return sqlBuilder.toString();
	}
	/**
	 * 返回当前搜索条件满足的 总数量 sql
	 * @return
	 */
	public String getSqlTotal() {
		String sqlFields = " count(*) as rowcount ";
		String sqlWhere = " ";
		//
		if (this.condition != null) {
			sqlWhere = " WHERE " + JStringUtils.join(this.condition, " AND ");
		}
		//
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT ").append(sqlFields).append(" FROM ").append(this.tableName).append(sqlWhere);
		return sqlBuilder.toString();
	}

	/**
	 * 设置获取那些字段
	 * @param fieldsStr
	 */
	public void setFields(String fieldsStr) {
		this.fields = fieldsStr.split(",");
		for (int i = 0, l = this.fields.length; i < l; i++) {
			this.fields[i] = this.fields[i].trim();
		}
	}
	public void setFields(String[] fieldsArr) {
		this.fields = fieldsArr;
	}

	
	/**
	 * 不分页 随意读取时用
	 * @param startIndex
	 * @param count
	 */
	public void setLimit(int startIndex, int count){
		this.getPage().setStartIndex(startIndex);
		this.getPage().setPageSize(count);
	}

	/**
	 * 添加搜索条件 字符串格式 
	 * @param c 如: a=b 或 a>0
	 */
	public JSQLHelper addCondition(String c) {
		if (this.condition == null) {
			this.condition = new ArrayList<String>();
		}
		this.condition.add(c);
		return this;
	}

	/**
	 * 添加排序顺序
	 * @param fieldName 字段名
	 * @param orderMode DESC or ASC
	 * @return
	 */
	public JSQLHelper addOrderBy(String fieldName, String orderMode) {
		if (this.orderby == null) {
			this.orderby = new ArrayList<String>();
		}
		this.orderby.add(fieldName + " " + orderMode);
		return this;
	}

	//
	public PageBase getPage() {
		return page;
	}

	public void setPage(PageBase page) {
		this.page = page;
	}
}
