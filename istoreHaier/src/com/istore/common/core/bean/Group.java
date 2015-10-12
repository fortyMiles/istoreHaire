package com.istore.common.core.bean;

public class Group {
	private long xgroup_id;
	private String xgroup_name;
	private String description;
	private String store_id;
	private String language_id;
	
	public long getXgroup_id() {
		return xgroup_id;
	}
	public void setXgroup_id(long xgroup_id) {
		this.xgroup_id = xgroup_id;
	}
	public String getXgroup_name() {
		return xgroup_name;
	}	
	public void setXgroup_name(String xgroup_name) {
		this.xgroup_name = xgroup_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
