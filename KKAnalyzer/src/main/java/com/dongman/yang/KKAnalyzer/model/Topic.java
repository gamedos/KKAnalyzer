package com.dongman.yang.KKAnalyzer.model;

import java.util.HashMap;
import java.util.Map;

public class Topic {

	private int id;
	private int labelId;
	private int gid;
	private String title;
	private String description;

	private int comicsCount;
	private String verticalImageUrl;
	private int createdAt;
	private int updatedAt;

	private int orderNum;

	private String authName;

	private int updateDay;

	//
	public Topic() {
	}
	//

	//
	public int getId() {
		return id;
	}

	/**
	 */
	public Topic(Map data) {
		Map user = (Map) data.get("user");
		this.id = ((Integer) data.get("id")).intValue();
		this.labelId = ((Integer) data.get("label_id")).intValue();
		this.gid = ((Integer) data.get("gid")).intValue();
		this.title = (String) data.get("title");
		this.description = (String) data.get("description");
		this.comicsCount = ((Integer) data.get("comics_count")).intValue();
		this.verticalImageUrl = (String) data.get("vertical_image_url");
		this.createdAt = ((Integer) data.get("created_at")).intValue();
		this.updatedAt = ((Integer) data.get("updated_at")).intValue();
		this.orderNum = ((Integer) data.get("order")).intValue();
		//
		this.authName = (String) user.get("nickname");
		//
		this.updateDay = data.get("updateDay") == null? 0 : ((Integer) data.get("updateDay")).intValue();
	}
	//
	public Map<String,Object> getMapData(){
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("id", this.getId());
		data.put("labelId", this.getLabelId());
		data.put("gid", this.getGid());
		data.put("title", this.getTitle());
		data.put("description", this.getDescription());
		data.put("comicsCount", this.getComicsCount());
		data.put("verticalImageUrl", this.getVerticalImageUrl());
		data.put("createdAt", this.getCreatedAt());
		data.put("updatedAt", this.getUpdatedAt());
		data.put("orderNum", this.getOrderNum());
		data.put("authName", this.getAuthName());
//		data.put("updateDay", this.getUpdateDay());
		return data;
	}
	//
	public void setId(int id) {
		this.id = id;
	}

	public int getLabelId() {
		return labelId;
	}

	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getComicsCount() {
		return comicsCount;
	}

	public void setComicsCount(int comicsCount) {
		this.comicsCount = comicsCount;
	}

	public String getVerticalImageUrl() {
		return verticalImageUrl;
	}

	public void setVerticalImageUrl(String verticalImageUrl) {
		this.verticalImageUrl = verticalImageUrl;
	}

	public int getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(int createdAt) {
		this.createdAt = createdAt;
	}

	public int getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(int updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}


	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public int getUpdateDay() {
		return updateDay;
	}

	public void setUpdateDay(int updateDay) {
		this.updateDay = updateDay;
	}


}
