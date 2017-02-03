package com.dongman.yang.KKAnalyzer.model;

import java.util.Map;

public class Genre {

	private int gid;
	private String gtitle;
	
	public Genre(){
		
	}
	//
	public void setMapData(Map<String , Object> data){
		this.setGid((Integer)data.get("gid"));
		this.setGtitle((String)data.get("gtitle"));
	}
	//
	
	
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getGtitle() {
		return gtitle;
	}
	public void setGtitle(String gtitle) {
		this.gtitle = gtitle;
	}
	
}
