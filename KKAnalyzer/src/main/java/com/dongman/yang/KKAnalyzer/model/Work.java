package com.dongman.yang.KKAnalyzer.model;

import java.util.HashMap;
import java.util.Map;

public class Work {

	private int wid;
	private int wstate;
	private int windex;
	private int createtime;
	private int endtime;
	private String temp;
	//
	public Work(){
		this.wid = 0;
		this.wstate = 0;
		this.windex = 0;
		this.createtime = 0;
		this.endtime = 0;
	}
	//
	public Work(Map<String , Object> data){
		this.setWid((Integer)data.get("wid"));
		this.setWindex((Integer)data.get("windex"));
		this.setWstate((Integer)data.get("wstate"));
		this.setCreatetime((Integer)data.get("createtime"));
		this.setEndtime((Integer)data.get("endtime"));
		this.setTemp((String)data.get("temp"));
	}
	//
	public void setMapData(Map<String , Object> data){
		this.setWid((Integer)data.get("wid"));
		this.setWindex((Integer)data.get("windex"));
		this.setWstate((Integer)data.get("wstate"));
		this.setCreatetime((Integer)data.get("createtime"));
		this.setEndtime((Integer)data.get("endtime"));
		this.setTemp((String)data.get("temp"));
	}
	//
	public Map<String,Object> getMapData(){
		Map<String,Object> data = new HashMap<String,Object>();
			data.put("wid", this.getWid());
			data.put("wstate", this.getWstate());
			data.put("windex", this.getWindex());
			data.put("createtime", this.getCreatetime());
			data.put("endtime", this.getEndtime());
			data.put("temp", this.getTemp());
		return data;
	}
	//
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public int getWstate() {
		return wstate;
	}
	public void setWstate(int wstate) {
		this.wstate = wstate;
	}
	public int getWindex() {
		return windex;
	}
	public void setWindex(int windex) {
		this.windex = windex;
	}
	public int getCreatetime() {
		return createtime;
	}
	public void setCreatetime(int createtime) {
		this.createtime = createtime;
	}
	public int getEndtime() {
		return endtime;
	}
	public void setEndtime(int endtime) {
		this.endtime = endtime;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	
	@Override
	public String toString() {
		return "wid = " + this.wid +
				    " , windex = " + this.windex +
				    " , wstate = " + this.wstate +
				    " , createtime = " + this.createtime +
				    " , endtime = " + this.endtime
				    ;
	}
	
	
}
